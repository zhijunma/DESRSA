package com.cn.school.dto.forms.usermanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class GetUserViewForm extends UserContextViewForm {
    /**
     * guid
     */
    @NotBlank
    private Long guid;
    /**
     * 密码
     */
    @NotBlank(message = "登录账号或密码不能为空")
    private String password;
    /**
     * 手机号码
     */
    @NotNull(message = "手机号码不能为空")
    private String mobilePhone;
}
