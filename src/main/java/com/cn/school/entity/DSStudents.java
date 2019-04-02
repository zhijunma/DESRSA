package com.cn.school.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author:HuMin Date:2019/3/1
 * Time:15:50
 */
@Data
public class DSStudents {

    /**
     * 主键id
     */
    private Long guid;

    /**
     * openid 微信唯一标识
     */
    private String openId;

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
     * 缴费状态  0：未交费 1 ：以缴费
     */
    private Integer status;

    /**
     * 分期guid
     */
    private Long stagesGuid;

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
