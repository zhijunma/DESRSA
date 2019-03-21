package com.cn.school.dto.forms.stagesmanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.util.List;

/**
 * @author mazhijun
 */
@Data
public class DeleteStagesViewForm extends UserContextViewForm {
    /**
     * guid
     */
    private List<Long> guidList;

}
