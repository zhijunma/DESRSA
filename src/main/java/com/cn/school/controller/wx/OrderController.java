package com.cn.school.controller.wx;

import com.cn.school.dto.info.bo.AddOrderBO;
import com.cn.school.service.wx.OrderService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:mzj 2019/3/26
 * date:9:20
 */

@Controller
@RequestMapping("/wx/order")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    /**
     * 添加订单
     *
     * @return
     */
    @PostMapping(value = "/addOrder")
    @ResponseBody
    public RestResponse register(@Validated @RequestBody RestRequest<AddOrderBO> request) {
        AddOrderBO addOrderBO = request.getBody();
        return orderService.addOrder(addOrderBO);
    }
}
