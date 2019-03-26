package com.cn.school.controller.wx;

import com.cn.school.dto.forms.pay.InitPayViewForm;
import com.cn.school.service.GateWayService;
import com.cn.school.utils.response.RestResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api("支付相关")
@RestController
public class PayController {
    @Autowired
    private GateWayService service;

    @PostMapping(value = "/pay/payment1")
    @ResponseBody
    public RestResponse initPay(@Validated @RequestBody InitPayViewForm viewForm) {
        System.out.println("支付请求开始=======================");
        String method = viewForm.getMethod();
        try {
            if ("submitOrderInfo".equals(method)) {
                service.pay(viewForm);
//            } else if ("queryOrder".equals(method)) {
//                service.query(viewForm);
//            } else if ("queryRefund".equals(method)) {
//                service.refundQuery(viewForm);
//            } else if ("submitRefund".equals(method)) {
//                service.refund(viewForm);
            }
        } catch (Exception e) {
            log.error("操作失败，原因：", e);
        }
        return null;

    }
}

