package com.cn.school.dto.forms.usermanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.util.List;

@Data
public class DeleteGroupPurchaseViewForm extends UserContextViewForm {
    /**
     * 主键id
     */
    private List<Long> guidList;

}
