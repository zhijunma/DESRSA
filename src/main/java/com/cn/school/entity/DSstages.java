package com.cn.school.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author:HuMin Date:2019/3/18
 * Time:20:12
 */
@Data
public class DSstages {

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
     * 分期活动金额
     */
    private BigDecimal money;

    /**
     * 状态
     */
    private Integer status;

    private Long addUserId;
    private LocalDateTime addTime;
    private String addUser;

    private LocalDateTime modTime;
    private String modUser;
    private Long modUserId;

    private Boolean deleteFlag;
}
