package com.cn.school.dto.forms;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author:HuMin Date:2019/3/1
 * Time:16:45
 */
@Data
public class WxInsertUserViewForm extends UserContextViewForm {

    /**
     * 用户名
     */
    @NotBlank
    private String userName;
//
//    /**
//     * 用户密码
//     */
//    @NotBlank
//    private String password;
//
//    /**
//     * 确认密码
//     */
//    @NotBlank
//    private String passwordCheck;

    /**
     * 电话号码
     */
    @NotBlank
    private String mobilePhone;

    /**
     * 验证码
     */
    @NotBlank
    @Size(min = 6, max = 6, message = "输入正确的验证码")
    private String code;
}
