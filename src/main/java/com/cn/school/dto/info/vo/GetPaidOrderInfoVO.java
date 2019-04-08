package com.cn.school.dto.info.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GetPaidOrderInfoVO {
    /**
     * guid
     */
    private Long guid;

    /**
     * 商户订单号
     */
    /**
     * 商品描述
     */
    private String body;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 附加信息
     */
    private String attach;
    /**
     * 总金额
     */
    private Integer totalFee;

    /**
     * 订单生成时间
     */
    private String timeStart;
    /**
     * 订单过期时间
     */
    private String timeExpire;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 电话号码
     */
    private String mobilePhone;
    /**
     * 应缴费
     */
    private BigDecimal payable;

    /**
     * 已缴费
     */
    private BigDecimal paid;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 银行名称
     */
    private String bankType;
    /**
     * 添加人
     */
    private LocalDateTime addTime;
    private String addUser;
}
