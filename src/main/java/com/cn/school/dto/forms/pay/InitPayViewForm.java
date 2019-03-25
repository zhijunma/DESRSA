package com.cn.school.dto.forms.pay;

import lombok.Data;

@Data
public class InitPayViewForm {
    /**
     * 商品描述
     */
    private String body;
    /**
     * 用户openid
     */
    private String sub_openid;
    /**
     * 总金额
     */
    private Integer total_fee;
    /**
     * 通知地址
     */
    private String notify_url;
}
