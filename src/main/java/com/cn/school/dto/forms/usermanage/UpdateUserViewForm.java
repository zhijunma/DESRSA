package com.cn.school.dto.forms.usermanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class UpdateUserViewForm extends UserContextViewForm {
    /**
     * guid
     */
    @NotNull
    private Long guid;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号码
     */
    private String mobilePhone;
}
