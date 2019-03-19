package com.cn.school.dto.forms.stagesmanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author:HuMin Date:2019/3/18
 * Time:19:49
 */
@Data
public class StagesViewForm extends UserContextViewForm {
    /**
     * guid
     */
    @NotBlank
    private Long guid;


}
