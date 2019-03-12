package com.cn.school.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author:HuMin Date:2019/3/4
 * Time:15:06
 */
@Data
@Table(name = "driver_school_code")
public class DSCode {

    /**
     * 手机号码
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 短信验证码
     */
    @Column(name = "code")
    private String code;

    /**
     * 短信模板code
     */
    @Column(name = "code_type")
    private String codeType;

    /**
     * 验证码状态
     */
    @Column(name = "status")
    private Integer status;

    @Column(name = "add_user_id")
    private Long addUserId;
    @Column(name = "add_time")
    private LocalDateTime addTime;
    @Column(name = "add_user")
    private String addUser;

    @Column(name = "mod_time")
    private LocalDateTime modTime;
    @Column(name = "mod_user")
    private String modUser;
    @Column(name = "mod_user_id")
    private Long modUserId;

    @Column(name = "delete_flag")
    private Boolean deleteFlag = false;
}
