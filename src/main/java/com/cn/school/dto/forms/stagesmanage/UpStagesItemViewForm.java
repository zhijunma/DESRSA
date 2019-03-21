package com.cn.school.dto.forms.stagesmanage;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author:HuMin Date:2019/3/18
 * Time:19:50
 */
@Data
public class UpStagesItemViewForm {

    /**
     * 还款金额
     */
    private BigDecimal repayAmount;

    /**
     * 还款期次
     */
    private Integer repayIssue;

}
