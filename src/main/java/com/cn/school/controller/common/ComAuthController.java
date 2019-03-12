package com.cn.school.controller.common;

import com.cn.school.dto.forms.auth.UserViewForm;
import com.cn.school.dto.info.vo.UserContextVO;
import com.cn.school.service.common.AuthService;
import com.cn.school.utils.Result;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:HuMin Date:2019/3/4
 * Time:16:14
 */
@RestController
@Slf4j
public class ComAuthController {
    @Autowired
    private AuthService authService;

    /**
     * @param restRequest
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserContextVO login(@RequestBody @Validated UserViewForm restRequest,
                               HttpServletRequest request, HttpServletResponse response) {
        return authService.login(restRequest, request, response);
    }

    /**
     * 登出
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<String> logout(HttpServletRequest request, HttpServletResponse response) {
        return authService.logout(request, response);
    }


}
