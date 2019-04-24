package com.cn.school.service.mzj;//package com.cn.school.utils;


import com.cn.school.FormView.GetRSAViewForm;
import com.cn.school.entity.mzj.DSRSA;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.cn.school.SchoolApplication.class)
public class Test {

    @Autowired
   CustomDESService customDESService;
    @Autowired
    CustomRSAService customRSAService;
    @org.junit.Test
    public void Ts(){
        String word = "测试";
//    customDESService.addDES(word,"加密方式");
//    word = customDESService.getDES(7L);
//        System.out.println(word);
//        customRSAService.addRSA(word,512);
        DSRSA dsrsa = customRSAService.getRSAPasswordKey(2L);
        GetRSAViewForm form = new GetRSAViewForm();
        form.setPassword(dsrsa.getPassword());
        form.setPrivateKey(dsrsa.getPrivateKey());
        System.out.println(customRSAService.getRSA(form));

    }
}