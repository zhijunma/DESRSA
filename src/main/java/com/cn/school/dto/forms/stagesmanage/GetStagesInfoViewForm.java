package com.cn.school.dto.forms.stagesmanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

/**
 * @author:HuMin Date:2019/3/18
 * Time:19:49
 */
@Data
public class GetStagesInfoViewForm extends UserContextViewForm {

    /**
     * guid
     */
    private Long guid;

    /**
     * name
     */
    private String name;
}
