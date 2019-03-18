package com.cn.school.service.web.impl;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import com.cn.school.dto.forms.auth.UserViewForm;
import com.cn.school.dto.info.po.LoginUserPO;
import com.cn.school.dto.info.vo.UserContextVO;
import com.cn.school.entity.DSUser;
import com.cn.school.mapper.wx.UserMapper;
import com.cn.school.service.web.LoginService;
import com.cn.school.utils.JwtUtil;
import com.cn.school.utils.RedisUtil;
import com.cn.school.utils.response.RestResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author:HuMin Date:2019/3/4
 * Time:17:09
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Value("${redisActiveTime}")
    private String redisActiveTime;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse login(UserViewForm viewForm) {
        LoginUserPO loginUserPO = new LoginUserPO();
        //手机号
        loginUserPO.setMobilePhone(viewForm.getMobile());
        //查询 user的验
        DSUser user = userMapper.getUserInfoByMobilRole(loginUserPO);
        LoginUserPO loginUser = new LoginUserPO();
        loginUser.setMobilePhone(viewForm.getMobile());
        loginUser.setGuid(user.getGuid());
        //验
        loginUser.setSalt(user.getSalt());
        //密码
        loginUser.setPassword(DigestUtils.sha256Hex(DigestUtils.sha256Hex(viewForm.getPassword()) + user.getSalt()));
        loginUser.setModTime(LocalDateTime.now());
        loginUser.setModUser(user.getUserName());
        loginUser.setModUserId(user.getGuid());
        Integer num = userMapper.updateUserLogin(loginUser);
        if (num <= 0) {
            return RestResponse.error("登录失败！");
        }

        //存入缓存
        String token = jwtUtil.generateToken(user);
        UserContextViewForm userContextViewForm = new UserContextViewForm();
        userContextViewForm.setCurrName(user.getUserName());
        userContextViewForm.setCurrId(user.getGuid());
        userContextViewForm.setCurrRole(user.getRole());
        userContextViewForm.setCurrTel(user.getMobilePhone());
        redisUtil.set(token, userContextViewForm, Long.valueOf(redisActiveTime).longValue());

        UserContextVO userContextVO = new UserContextVO();
        userContextVO.setCurrName(user.getUserName());
        userContextVO.setCurrId(user.getGuid());
        userContextVO.setCurrRole(user.getRole());
        userContextVO.setCurrTel(user.getMobilePhone());
        userContextVO.setToken(token);

        return RestResponse.success(userContextVO);
    }
}
