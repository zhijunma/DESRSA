package com.cn.school.dto.forms.pay;

import lombok.Data;

/**
 * @Author: leiyunlong
 * @Date: 2019/3/26 10:36
 * @Version 1.0
 */
@Data
public class queryViewForm {
    /**
     * 接口类型
     */
    private String servise;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 平台订单号
     */
    private String transactionId;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;

}
