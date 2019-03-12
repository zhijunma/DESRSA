package com.cn.school.controller.common;

import com.cn.school.dto.forms.ComSendCodeViewForm;
import com.cn.school.service.common.SmsService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

/**
 * 阿里云短信相关
 *
 * @author:HuMin Date:2019/3/4
 * Time:11:02
 */

@RestController
@RequestMapping("/com")
public class ComSmsController {

    @Autowired
    private SmsService smsService;

    /**
     * 发送短信验证码（所有手机号都可以发送）
     *
     * @param request
     * @return
     */
    @PermitAll
    @RequestMapping(value = "/sendCode", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse sendCode(@RequestBody RestRequest<ComSendCodeViewForm> request) {
        ComSendCodeViewForm viewForm = request.getBody();
        return smsService.sendCode(viewForm);
    }

}