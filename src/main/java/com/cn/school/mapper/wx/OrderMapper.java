package com.cn.school.mapper.wx;

import com.cn.school.entity.DSOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {
    /**
     * 添加订单
     *
     * @param dsOrder
     * @return
     */
    Integer addOrder(@Param("dsOrder") DSOrder dsOrder);

    /**
     * 对接收到通知参数里的订单号out_trade_no和订单金额total_fee和自身业务系统的订单和金额做校验
     *
     * @param dsOrder
     * @return
     */
    DSOrder getOrder(@Param("dsOrder") DSOrder dsOrder);

    /**
     * 修改订单状态
     *
     * @param dsOrder
     * @return
     */
    Integer updateOrderStatus(@Param("dsOrder") DSOrder dsOrder);

}
