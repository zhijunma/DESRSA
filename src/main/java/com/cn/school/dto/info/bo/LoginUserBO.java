package com.cn.school.dto.info.bo;

import lombok.Data;

/**
 * @author:HuMin Date:2019/3/12
 * Time:14:49
 */
@Data
public class LoginUserBO {
    /**
     * 电话号码
     */
    private String mobilePhone;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 验
     */
    private String salt;
}
