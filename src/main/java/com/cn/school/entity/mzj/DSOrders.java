package com.cn.school.entity.mzj;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单实体
 * @author Administrator
 */
@Data
public class DSOrders {
    /**
     * 主键guid
     */
    private Long guid;
    /**
     * 商品主键
     */
    private Long goodsGuid;
    /**
     * 商品数量
     */
    private Long quantity;
    /**
     * 商品总价
     */
    private BigDecimal total;
    /**
     * 添加者
     */
    private Long addUserGuid;
    /**
     * 订单状态 0,未付款，1 已付款
     */
    private Long state;
    /**
     * 修改时间
     */
    private LocalDateTime addTime;
    /**
     * 0：未删除 1：删除
     */
    private Boolean deleteFlag;
}
