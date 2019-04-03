package com.cn.school.controller.wx;

import com.cn.school.dto.forms.pay.InitPayViewForm;
import com.cn.school.dto.forms.pay.queryViewForm;
import com.cn.school.service.GateWayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(description = "PayController", tags = {"支付相关"})
@RestController
@RequestMapping("/pay/payment")
public class PayController {

    @Autowired
    private GateWayService service;

    /**
     * 初始化请求API
     *
     * @param viewForm
     * @return
     */
    @PostMapping(value = "/submitOrderInfo")
    @ResponseBody
    @ApiOperation(value = "初始化请求API")
    public Map<String, String> initPay(@Validated @RequestBody InitPayViewForm viewForm) {
        System.out.println("支付请求开始=======================");
//        String method = viewForm.getMethod();
        Map<String, String> pay = new HashMap<>();
        try {
             pay = service.pay(viewForm);
//            if ("submitOrderInfo".equals(method)) {
//            } else if ("queryOrder".equals(method)) {
//                service.query(viewForm);
//            } else if ("queryRefund".equals(method)) {
//                service.refundQuery(viewForm);
//            } else if ("submitRefund".equals(method)) {
//                service.refund(viewForm);
//            }
        } catch (Exception e) {
            log.error("操作失败，原因：", e);
        }
        return pay;
    }

    /**
     * 订单查询
     *
     * @param viewForm
     * @return
     */
    @PostMapping(value = "/queryOrder")
    @ResponseBody
    public Map<String, Object> query(@Validated @RequestBody queryViewForm viewForm) {
        System.out.println("订单查询开始=======================");
        Map<String, Object> query = new HashMap<>();
        try {
            query = service.query(viewForm);
        } catch (Exception e) {
            log.error("操作失败，原因：", e);
        }
        return query;
    }
}

