package com.cn.school.dto.forms.usermanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;
@Data
public class GetUserViewForm extends UserContextViewForm {
    /**
     * guid
     */
    private Long guid;
}
