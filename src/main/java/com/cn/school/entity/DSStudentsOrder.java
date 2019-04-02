package com.cn.school.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: leiyunlong
 * @Date: 2019/4/1 11:02
 * @Version 1.0
 */
@Data
public class DSStudentsOrder {
    /**
     * 主键id
     */
    private Long guid;
    /**
     * 用户名
     */
    private String userName;


    /**
     * 电话号码
     */
    private String mobilePhone;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 应缴费
     */
    private BigDecimal payable;

    /**
     * 已缴费
     */
    private BigDecimal paid;

    /**
     * 驾照等级
     */
    private String driverLevel;
    /**
     * 订单（表)时间
     */
    private LocalDateTime dso_add_time;
    /**
     * 金额
     */
    private Integer totalFee;
    /**
     * 学员表id
     */
    private Long studentsGuid;
    /**
     * 订单表id
     */
    private Long orderGuid;
    /**
     * 订单状态  0：未交费 1：已缴费
     */
    private int status;

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
