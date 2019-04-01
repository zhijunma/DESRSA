package com.cn.school.dto.info.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: leiyunlong
 * @Date: 2019/4/1 16:54
 * @Version 1.0
 */
@Data
public class GetStudentOrderVO {
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
}
