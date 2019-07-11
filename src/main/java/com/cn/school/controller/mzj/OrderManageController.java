package com.cn.school.controller.mzj;

import com.cn.school.FormView.AddUserOrderViewForm;
import com.cn.school.service.mzj.OrdersManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@Api(description = "OrderManageController",tags = {"订单管理"})
@ApiModel(value="订单管理",description="订单管理")
@RequestMapping(value = "/Order")
public class OrderManageController {
    @Autowired
    OrdersManageService ordersManageService;

    @PostMapping(value = "/addOrder")
    @ApiOperation(value="添加订单")
    @ResponseBody
    public String addUserOrder(AddUserOrderViewForm form) {
        return ordersManageService.addUserOrder(form);
    }
}
