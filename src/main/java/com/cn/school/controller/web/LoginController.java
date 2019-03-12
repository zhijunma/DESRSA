package com.cn.school.controller.web;

import com.cn.school.dto.forms.auth.UserViewForm;
import com.cn.school.service.web.LoginService;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param viewForm
     * @return
     */
    @PostMapping(value = "login")
    public RestResponse LoginCon(@RequestBody @Validated UserViewForm viewForm) {

        RestResponse restResponse = loginService.login(viewForm);

        return restResponse;
    }


}