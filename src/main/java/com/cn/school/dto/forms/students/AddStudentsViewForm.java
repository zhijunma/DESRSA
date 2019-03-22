package com.cn.school.dto.forms.students;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author:HuMin Date:2019/3/19
 * Time:11:55
 */
@Data
public class AddStudentsViewForm {
    /**
     * 用户名
     */
    @NotBlank
    private String userName;
    /**
     * 电话号码
     */
    @NotBlank
    @Size(min = 11, max = 11, message = "输入正确的电话号码")
    private String mobilePhone;

    /**
     * 验证码
     */
    @NotBlank
    @Size(min = 6, max = 6, message = "输入正确的验证码")
    private String code;
    /**
     * 身份证号
     */
    @NotBlank
    @Size(min = 18, max = 18, message = "输入正确的身份证号码")
    private String idCard;
}
