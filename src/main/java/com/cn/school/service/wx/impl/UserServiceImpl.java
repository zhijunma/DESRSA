package com.cn.school.service.wx.impl;

import com.cn.school.dto.forms.WxInsertUserViewForm;
import com.cn.school.dto.info.po.InsertUserPO;
import com.cn.school.mapper.wx.UserMapper;
import com.cn.school.mapstruct.UserMapStruct;
import com.cn.school.service.wx.UserService;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author:HuMin Date:2019/2/26
 * Time:10:42
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userapper;
    @Autowired
    private UserMapStruct userMapStruct;
    /**
     * 学员
     */
    private final Integer stuRole = 1;

    /**
     * 用户注册
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse insertUser(WxInsertUserViewForm viewForm) {
//        if (!viewForm.getPassword().equals(viewForm.getPasswordCheck())) {
//            throw new RuntimeException("两次密码不一致！");
//        }
        InsertUserPO insertUserPO = new InsertUserPO();
        insertUserPO.setUserName(viewForm.getUserName());
        insertUserPO.setMobilePhone(viewForm.getMobilePhone());
        //设置角色 1：学员
        insertUserPO.setRole(stuRole);
        //默认密码
        String salt = UUID.randomUUID().toString();
//        insertUserPO.setPassword(DigestUtils.sha256Hex(DigestUtils.sha256Hex(viewForm.getPassword()) + salt));
        //身份证
        insertUserPO.setIdCard("0");
        //盐
//        insertUserPO.setSalt(salt);
        //盐缴状态
        insertUserPO.setStatus(0);
        //删除
        insertUserPO.setDeleteFlag(false);
        insertUserPO.setAddTime(LocalDateTime.now());
        insertUserPO.setAddUser("000000");
        insertUserPO.setAddUserId(0L);
        insertUserPO.setModTime(LocalDateTime.now());
        insertUserPO.setModUser("000000");
        insertUserPO.setModUserId(0L);
        Integer num = userapper.insertUser(insertUserPO);
        return RestResponse.success("新增成功");
    }
}
