package com.cn.school.controller.web;

import com.cn.school.dto.forms.order.GetOrdersViewForm;
import com.cn.school.dto.forms.order.OrderViewForm;
import com.cn.school.service.web.OrderManageService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:mazhijun Date:2019/3/26
 * Time:11:02
 */

@RestController
@RequestMapping("/web/orders")
public class OrdersManageController {

    @Autowired
    OrderManageService orderManageService;
    /**
     * 管理员一览订单
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/getOrders")
    public RestResponse getOrders(@RequestBody @Validated RestRequest<GetOrdersViewForm> request) {
        GetOrdersViewForm viewForm = request.getBody();
        return orderManageService.getOrders(viewForm);
    }
    /**
     * 修改订单
     * @param request
     * @return
     */
    @PostMapping(value = "/updateOrderStatus", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse updateOrderStatus(@RequestBody @Validated RestRequest<OrderViewForm> request) {
        OrderViewForm viewForm = request.getBody();
        return orderManageService.updateOrderStatus(viewForm);
    }
}