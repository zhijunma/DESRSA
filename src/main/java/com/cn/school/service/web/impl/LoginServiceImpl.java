package com.cn.school.service.web.impl;

import com.cn.school.entity.DSUser;
import com.cn.school.mapper.wx.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author:HuMin Date:2019/3/4
 * Time:17:09
 */
@Service
public class LoginServiceImpl {
    @Autowired
    private UserMapper userMapper;
    public DSUser Login(String username, String password) {
        DSUser user=userMapper.login(username,password);
        if(ObjectUtils.isEmpty(user)){
            return null;  //throw new UserValidationException("用户不存在");
        }else {
            return userMapper.login(username,password);
        }
    }

}
