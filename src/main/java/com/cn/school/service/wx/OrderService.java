package com.cn.school.service.wx;

import com.cn.school.dto.info.bo.AddOrderBO;
import com.cn.school.utils.response.RestResponse;
import org.apache.ibatis.annotations.Param;

/**
 * @author:mzj Date:2019/3/25
 * Time:17:40
 */
public interface OrderService {
    /**
     * 添加订单
     *
     * @param addOrderBO
     * @return
     */
    RestResponse addOrder(@Param("addOrderBO") AddOrderBO addOrderBO);
}
