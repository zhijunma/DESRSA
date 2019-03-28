package com.cn.school.dto.info.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author:HuMin Date:2019/3/1
 * Time:15:50
 */
@Data
public class GetCostInfoVO {

    /**
     * guid
     */
    private Long guid;

    /**
     * 驾照类型（等级）
     */
    private String driverLevel;

    /**
     * 金额
     */
    private BigDecimal driverMoney;

}
