package com.cn.school.service.mzj;

import com.cn.school.FormView.GetRSAViewForm;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.cn.school.SchoolApplication.class)
public class Test {

//    @Autowired
//   CustomDESService customDESService;
    @Autowired
    CustomRSAService customRSAService;
    @org.junit.Test
    public void Ts(){
//        GetRSAViewForm form = new GetRSAViewForm();
//        form.setId(2L);
//        form.setRsaId(2L);
//        form.setPassword(customRSAService.getRSAPasswordById(form));
//        form.setPrivateKey(customRSAService.getRSAPrivateKeyById(form));
        customRSAService.addRSA("测试4",512);
//        customRSAService.getRSAPasswordById(form);
//        customRSAService.getRSAPrivateKeyById(form);
//        customRSAService.getRSAPublicKeyById(form);
//        System.out.println(customRSAService.getRSA(form));
//        customRSAService.getRSA()
    }
}
