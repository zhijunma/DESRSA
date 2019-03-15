package com.cn.school.dto.forms.usermanage;

import lombok.Data;

@Data
public class GetStuViewForm {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 电话号码
     */
    private String mobilePhone;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 缴费状态  0：未交费 1 ：以缴费 3：空闲（教练员） 4：忙碌（教练员）
     */
    private Integer status;
}
