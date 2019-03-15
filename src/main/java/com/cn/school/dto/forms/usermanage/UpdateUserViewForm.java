package com.cn.school.dto.forms.usermanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

@Data
public class UpdateUserViewForm extends UserContextViewForm {
    /**
     * guid
     */
    private Long guid;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号码
     */
    private String mobilePhone;
    /**
     * 身份证号
     */
    private String idCard;
}
