package com.cn.school.dto.forms.pay;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InitPayViewForm {
    @NotBlank(message = "method 不能为为空")
    private String method;
    /**
     * 商品描述
     */
    @NotBlank
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
    @NotBlank
    private String notify_url;
}
