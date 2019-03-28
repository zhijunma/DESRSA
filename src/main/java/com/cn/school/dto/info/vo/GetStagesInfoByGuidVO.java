package com.cn.school.dto.info.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author:HuMin Date:2019/3/28
 * Time:10:54
 */
@Data
public class GetStagesInfoByGuidVO {

    /**
     * guid
     */
    private Long guid;
    /**
     * 外键guid
     */
    private Long stageGuid;
    /**
     * 还款金额
     */
    private BigDecimal repayAmount;

    /**
     * 还款期次
     */
    private Integer repayIssue;
}
