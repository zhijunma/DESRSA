package com.cn.school.controller.web;

import com.cn.school.dto.forms.user.UserInsertViewForm;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:HuMin Date:2019/3/4
 * Time:11:02
 */

@RestController
@RequestMapping("/web/user")
public class UserManageController {

    /**
     * 发送短信验证码（所有手机号都可以发送）
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse sendCode(@RequestBody RestRequest<UserInsertViewForm> request) {
        UserInsertViewForm viewForm = request.getBody();
        return null;
    }

}