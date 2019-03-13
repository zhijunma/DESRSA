package com.cn.school.dto.forms.usermanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class GetUserViewForm extends UserContextViewForm {
    /**
     * guid
     */
    @NotNull
    private Long guid;
}
