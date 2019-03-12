package com.cn.school.controller.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:HuMin Date:2019/3/4
 * Time:11:02
 */

@RestController
@RequestMapping("/web/user")
public class UserManageController {

    @RequestMapping("/login")
    public String index() {
        return "Hello World";
    }
    /**
     * 查看个人信息
     *
     */
}