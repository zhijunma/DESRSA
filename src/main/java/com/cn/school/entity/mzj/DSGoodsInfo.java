package com.cn.school.entity.mzj;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DSGoodsInfo {

    /**
     * 主键guid  无序输入 自增长
     */
    public Long guid;
    /**
     * 商品名称
     */
    public String goodsName;
    /**
     *图片地址
     */
    public String img;
    /**
     *价格
     */
    public BigDecimal price;
    /**
     *备注
     */
    public String remake;
    /**
     *是否是推荐  0，否，1，是
     */
    public Long recommend;
    /**
     *商品类型
     */
    public String type;
    /**
     *商品状态
     */
    public Long deleteFlage;
    /**
     *是否是新品
     */
    public Long isNew;
}
