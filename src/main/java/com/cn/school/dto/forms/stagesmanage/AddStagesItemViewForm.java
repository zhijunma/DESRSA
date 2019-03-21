package com.cn.school.dto.forms.stagesmanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author:HuMin Date:2019/3/18
 * Time:19:50
 */
@Data
public class AddStagesItemViewForm extends UserContextViewForm {

    /**
     * 还款金额
     */
    private BigDecimal repayAmount;

    /**
     * 还款期次
     */
    private Integer repayIssue;

}
