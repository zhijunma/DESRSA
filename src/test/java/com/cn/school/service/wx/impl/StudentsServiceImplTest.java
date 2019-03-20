package com.cn.school.service.wx.impl;

import com.cn.school.dto.forms.usermanage.InsertCoachViewForm;
import com.cn.school.service.web.UsersService;
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
public class StudentsServiceImplTest {

    @Autowired
    private UsersService usersService;


    @Test
    public void add() {
        InsertCoachViewForm viewForm = new InsertCoachViewForm();
        viewForm.setMobilePhone("13099113151");
        viewForm.setPassword("123456");
        viewForm.setIdCard("620121199311303838");
        viewForm.setUserName("马");
        usersService.addCoach(viewForm);
    }
}