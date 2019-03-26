package com.cn.school.controller.web;

import com.cn.school.dto.forms.EvaluateManage.AddEvaluateViewForm;
import com.cn.school.dto.forms.EvaluateManage.DeleteEvaluateViewForm;
import com.cn.school.dto.forms.EvaluateManage.GetEvaluateViewForm;
import com.cn.school.dto.forms.order.GetOrderViewForm;
import com.cn.school.dto.info.bo.AddOrderBO;
import com.cn.school.dto.info.vo.GetOrderInfoVO;
import com.cn.school.mapper.web.OrderManageMapper;
import com.cn.school.service.web.EvaluateManageService;
import com.cn.school.service.web.OrderManageService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:HuMin Date:2019/3/4
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
    public List getOrders(@RequestBody @Validated RestRequest<GetOrderViewForm> request) {
        GetOrderViewForm viewForm = request.getBody();
        AddOrderBO a = new AddOrderBO();
        a.setStatus(viewForm.getStatus());
        return orderManageService.getOrders(a);
    }

}