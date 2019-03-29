package com.cn.school.dto.info.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class GetStuInfoVO {
    /**
     * 主键id
     */
    private Long guid;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 电话号码
     */
    private String mobilePhone;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 缴费状态  0：未交费 1 ：以缴费
     */
    private Integer status;
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
}
