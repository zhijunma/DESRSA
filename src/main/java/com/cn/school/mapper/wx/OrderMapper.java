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
     * @param dsOrder
     * @return
     */
    Integer addOrder(@Param("dsOrder") DSOrder dsOrder);
}
