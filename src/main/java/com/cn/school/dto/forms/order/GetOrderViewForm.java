package com.cn.school.dto.forms.order;

import io.swagger.annotations.ApiOperation;
import lombok.Data;


@Data
public class GetOrderViewForm {
    /**
     * 订单状态
     */
    private Integer status;
}
