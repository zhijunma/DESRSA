package com.cn.school.entity.mzj;

import lombok.Data;

@Data
public class DSUserInfo {
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
}
