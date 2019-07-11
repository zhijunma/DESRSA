package com.cn.school.FormView;

import lombok.Data;

@Data
public class GetUserInfoViewForm {
    /**
     * guid
     */
    private Long guid;
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
    /**
     * qq
     */
    private Long qq;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 地址
     */
    private String address;
}
