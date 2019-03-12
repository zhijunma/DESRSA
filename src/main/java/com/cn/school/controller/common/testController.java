package com.cn.school.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:HuMin Date:2019/3/8
 * Time:10:50
 */
@Controller
public class testController {
    @RequestMapping(value = "/test")
    public String sendCode() {
        return "index.html";
    }


}
