package com.cn.school.dto.info.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author:HuMin Date:2019/3/28
 * Time:9:46
 */
@Data
public class GetStagesListByCostVO {
    /**
     * guid
     */
    private Long guid;
    /**
     * guid
     */
    private Long costId;
    /**
     * 分期活动名称
     */
    private String name;

    /**
     * 分期活动期次
     */
    private Integer issues;
    /**
     * 分期活动金额
     */
    private BigDecimal money;
}
