package com.cn.school.FormView;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetGoodsViewForm {
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     *图片地址
     */
    private String img;
    /**
     *价格
     */
    private BigDecimal price;
    /**
     *备注
     */
    private String remake;
    /**
     *是否是推荐  0，否，1，是
     */
    private Long recommend;
    /**
     *商品类型
     */
    private String type;
    /**
     *商品状态
     */
    private Long deleteFlage;
    /**
     *是否是新品
     */
    private Long isNew;
}
