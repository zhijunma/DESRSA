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
    private String mch_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 平台订单号
     */
    private String transaction_id;
    /**
     * 随机字符串
     */
    private String nonce_str;
    /**
     * 签名
     */
    private String sign;

}
