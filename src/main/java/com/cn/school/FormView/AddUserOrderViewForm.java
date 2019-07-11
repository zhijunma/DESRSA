package com.cn.school.FormView;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * @author Administrator
 */
@Data
public class AddUserOrderViewForm {
    /**
     * 商品总价格
     */
    private BigDecimal total;
    /**
     * 商品主键
     */
    private Long goodsGuid;
    /**
     * 商品数量
     */
    private Long quantity;
    /**
     * 添加者
     */
    private Long addUserGuid;
    /**
     * 修改时间
     */
    private LocalDateTime addTime;
}
