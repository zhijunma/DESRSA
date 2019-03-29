package com.cn.school.dto.forms.students;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author:HuMin Date:2019/3/29
 * Time:15:45
 */
@Data
public class AddInfoViewForm {
    /**
     * guid
     */
    private Long guid;

    /**
     * 应缴
     */
    private BigDecimal payable;

    /**
     * 驾照类型（等级）
     */
    private String driverLevel;
}
