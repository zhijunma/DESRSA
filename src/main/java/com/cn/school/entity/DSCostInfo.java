package com.cn.school.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author:HuMin Date:2019/3/1
 * Time:15:50
 */
@Data
public class DSCostInfo {
    /**
     * 主键id
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

    /**
     * 添加人
     */
    private Long addUserId;
    private String addUser;
    private LocalDateTime addTime;

    /**
     * 修改人
     */
    private Long modUserId;
    private String modUser;
    private LocalDateTime modTime;

    /**
     * 0：未删除 1：删除
     */
    private Boolean deleteFlag;

}
