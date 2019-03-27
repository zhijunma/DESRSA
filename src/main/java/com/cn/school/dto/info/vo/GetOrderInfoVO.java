package com.cn.school.dto.info.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetOrderInfoVO {
    /**
     * guid
     */
    private Long guid;
    /**
     * 接口类型
     */
    private String service;
    /**
     * 版本号
     */
    private String version;
    /**
     * 字符集
     */
    private String charset;
    /**
     * 签名方式
     */
    private String signType;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 原生js
     */
    private String isRaw;
    /**
     * 是否小程序支付 0，否 1，是
     */
    private String isMinipg;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 设备号
     */
    private String deviceInfo;
    /**
     * 操作员
     */
    private String opUserId;
    /**
     * 门店编号
     */
    private String opShopId;
    /**
     * 商品描述
     */
    private String body;
    /**
     *用户openid
     */
    private String subOpenid;
    /**
     * 公众号或小程序id
     */
    private String subAppid;
    /**
     * 附加信息
     */
    private String attach;
    /**
     * 总金额
     */
    private Integer totalFee;
    /**
     *电子发票
     */
    private Long needReceipt;
    /**
     * 终端IP
     */
    private String mchCreateIp;
    /**
     * 通知地址
     */
    private String notifyUrl;
    /**
     * 订单生成时间
     */
    private String timeStart;
    /**
     * 订单过期时间
     */
    private String timeExpire;
    /**
     * 商品标记
     */
    private String goodsTag;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 是否限制信用卡 0，否  1，是
     */
    private String limitCreditPay;
    /**
     * 签名
     */
    private String sign;
    /**
     * 订单状态 0，有效 1，失效
     */
    private Integer status;
    /**
     * 添加人
     */
    private Long addUserId;
    private LocalDateTime addTime;
    private String addUser;
    /**
     * 修改人
     */
    private LocalDateTime modTime;
    private String modUser;
    private Long modUserId;
    /**
     * 是否删除 0，否  1，是
     */
    private Boolean deleteFlag;
}
