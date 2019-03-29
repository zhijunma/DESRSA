package com.cn.school.dto.info.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetStagesInfoVO {
    /**
     * guid
     */
    private Long guid;
    /**
     * 分期活动名称
     */
    private String name;

    /**
     * 分期活动期次
     */
    private Integer issues;

    /**
     * 状态
     */
    private Integer status;

}
