package com.cn.school.service.common;

import com.cn.school.dto.forms.ComSendCodeViewForm;
import com.cn.school.utils.response.RestResponse;

/**
 * @author:HuMin Date:2019/3/4
 * Time:12:01
 */
public interface SmsService {
    /**
     * 发送短信验证码
     *
     * @param viewForm
     * @return
     */
    RestResponse sendCode(ComSendCodeViewForm viewForm);
}
