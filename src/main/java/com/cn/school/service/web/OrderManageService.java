package com.cn.school.service.web;

import com.cn.school.dto.info.bo.AddOrderBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author:mzj Date:2019/3/25
 * Time:17:40
 */
public interface OrderManageService {
    /**
     * 查看订单
     *
     * @param addOrderBO
     * @return
     */
    List getOrders(@Param("addOrderBO") AddOrderBO addOrderBO);
}
