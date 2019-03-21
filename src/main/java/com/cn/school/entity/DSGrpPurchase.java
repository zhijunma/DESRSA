package com.cn.school.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DSGrpPurchase {
    /**
     * 主键id
     */
    private Long guid;
    /**
     * 团购活动名称
     */
    private String gpNname;
    /**
     * 团购限定人数
     */
    private Integer peopleNum;
    /**
     * 驾照类型（等级）
     */
    private String driverLevel;
    /**
     * 团购总金额
     */
    private Long aggregateAmount;
    /**
     * 期次；团购项目可以分多少期（1：不能分期）
     */
    private Integer issue;
    /**
     * 优惠券 1：可以使用优惠券 0：不能使用优惠券
     */
    private Integer coupon;
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