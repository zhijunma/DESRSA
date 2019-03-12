package com.cn.school.service.wx.impl;

import com.cn.school.dto.forms.WxInsertUserViewForm;
import com.cn.school.service.wx.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author:HuMin Date:2019/3/5
 * Time:18:57
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.cn.school.SchoolApplication.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void insert() {
        WxInsertUserViewForm viewForm = new WxInsertUserViewForm();
        viewForm.setMobilePhone("13099113151");
        viewForm.setUserName("马");
//        viewForm.setPassword("123123qwe");
//        viewForm.setPasswordCheck("123123qwe");
        viewForm.setCode("000000");
        userService.insertUser(viewForm);

    }
}