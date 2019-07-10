package com.cn.school.FormView;

import lombok.Data;

@Data
public class GetUserInfoViewForm {

    /**
     * 电话
     */
    private Long mobilePhone;
    /**
     * 昵称
     */
    private String userName;
    /**
     * 密码
     */
    private String userPassword;
}
