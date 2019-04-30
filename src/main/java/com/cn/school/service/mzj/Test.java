package com.cn.school.service.mzj;

import com.cn.school.FormView.GetRSAViewForm;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.cn.school.SchoolApplication.class)
public class Test {

    @Autowired
   CustomDESService customDESService;
//    @Autowired
//    CustomRSAService customRSAService;
    @org.junit.Test
    public void Ts(){
    }
}
