package com.cn.school.mapper.web;

import com.cn.school.dto.info.bo.ManageOrderBO;
import com.cn.school.entity.DSOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderManageMapper {
    /**
     * 添加订单
     * @param dsOrder
     * @return
     */
    List getOrders(@Param("dsOrder") ManageOrderBO dsOrder);

    /**
     *
     * @param list
     * @param status
     * @param modUserId
     * @param modUser
     * @return
     */
    Integer updateOrderStatus(@Param("list") List<Long> list, @Param("status") Integer status, @Param("modUserId") Long modUserId, @Param("modUser") String modUser);

}
