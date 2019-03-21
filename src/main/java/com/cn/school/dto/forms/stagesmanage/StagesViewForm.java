package com.cn.school.dto.forms.stagesmanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.util.List;

/**
 * @author:HuMin Date:2019/3/18
 * Time:19:49
 */
@Data
public class StagesViewForm extends UserContextViewForm {
    /**
     * guid
     */
    private List<Long> guidList;
}
