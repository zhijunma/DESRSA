package com.cn.school.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author:HuMin Date:2019/3/8
 * Time:10:50
 */
@Controller
public class testController {
    @GetMapping(value = {"/","/index","/login"})
    public String sendCode() {
        System.out.println("______________________");
        return "index";
    }



}
