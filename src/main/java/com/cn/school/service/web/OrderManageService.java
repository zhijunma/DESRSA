package com.cn.school.service.web;

import com.cn.school.dto.forms.order.GetOrdersViewForm;
import com.cn.school.dto.forms.order.OrderViewForm;
import com.cn.school.utils.response.RestResponse;
import java.util.List;

/**
 * @author:mzj Date:2019/3/25
 * Time:17:40
 */
public interface OrderManageService {
    /**
     * 查看订单
     * @param viewForm
     * @return
     */
    RestResponse getOrders(GetOrdersViewForm viewForm);
    /**
     * 修改订单
     * @param viewForm
     * @return
     */
    RestResponse updateOrderStatus(OrderViewForm viewForm);
}
