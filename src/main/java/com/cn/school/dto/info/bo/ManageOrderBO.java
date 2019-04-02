package com.cn.school.dto.info.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class ManageOrderBO {
        /**
         * guidtotalFee
         */
        private Long guid;

        /**
         * 商户订单号
         */
        private String outTradeNo;

        /**
         * 商品描述
         */
        private String body;

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
         * 订单状态 0，生成订单 1，支付成功
         */
        private Integer status;

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
}
