package com.cn.school.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author:HuMin Date:2019/3/18
 * Time:20:12
 */
@Data
public class DSStagesItem {

    /**
     * 还款金额
     */
    private BigDecimal repayAmount;

    /**
     * 还款期次
     */
    private Integer repayIssue;

    private Long addUserId;
    private LocalDateTime addTime;
    private String addUser;

    private LocalDateTime modTime;
    private String modUser;
    private Long modUserId;

    private Boolean deleteFlag;

}
