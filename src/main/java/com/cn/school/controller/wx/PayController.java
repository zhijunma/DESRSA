package com.cn.school.controller.wx;

import com.cn.school.dto.forms.pay.InitPayViewForm;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
@Api("支付相关")
@Controller
public class PayController {
    public RestResponse initPay(@Validated @RequestBody RestRequest<InitPayViewForm> request) {
        return null;

    }
}

