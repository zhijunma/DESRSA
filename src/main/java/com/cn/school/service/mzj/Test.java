package com.cn.school.service.mzj;

import com.cn.school.FormView.GetGoodsViewForm;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.cn.school.SchoolApplication.class)
public class Test {

    @Autowired
    GoodsInfoService goodsInfoService;
    @Autowired
    UserLoginService userLoginService;
    @org.junit.Test
    public void Ts(){
        GetGoodsViewForm viewForm = new GetGoodsViewForm();

        goodsInfoService.getGoodsInfo(viewForm);
}
}
