package com.cn.school.controller.web;

import com.cn.school.dto.forms.auth.UserViewForm;
import com.cn.school.service.web.LoginService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse Login(@RequestBody @Validated RestRequest<UserViewForm> request) {
        UserViewForm viewForm = request.getBody();
        System.out.println("++++++++++++++++++++++++" + viewForm.getCurrName());
        RestResponse restResponse = loginService.login(viewForm);
        return restResponse;
    }

}