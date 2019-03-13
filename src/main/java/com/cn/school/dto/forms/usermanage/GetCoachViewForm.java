package com.cn.school.dto.forms.usermanage;

import lombok.Data;

@Data
public class GetCoachViewForm {
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
}
