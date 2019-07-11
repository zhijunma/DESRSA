package com.cn.school.mapper.mzj;

import com.cn.school.entity.mzj.DSOrders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 订单管理
 */
@Mapper
@Repository
public interface OrdersManageMapper {
    /**
     * 添加订单
     * @param ds
     * @return
     */
    Long addUserOrder(@Param("ds") DSOrders ds);
}
