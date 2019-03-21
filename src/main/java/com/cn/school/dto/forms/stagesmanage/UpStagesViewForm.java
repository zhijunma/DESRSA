package com.cn.school.dto.forms.stagesmanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author:HuMin Date:2019/3/18
 * Time:19:49
 */
@Data
public class UpStagesViewForm extends UserContextViewForm {

    /**
     * guid
     */
    private Long guid;
    /**
     * 分期活动名称
     */
    private String name;

    /**
     * 分期活动名称
     */
    private Integer issues;

    /**
     * 分期活动子表
     */
    private List<UpStagesItemViewForm> itemList;
}
