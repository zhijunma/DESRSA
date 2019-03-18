package com.cn.school.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author:HuMin Date:2019/3/4
 * Time:15:06
 */
@Data
public class DSCode {

    /**
     * 手机号码
     */
    private String mobilePhone;

    /**
     * 短信验证码
     */
    private String code;

    /**
     * 短信模板code
     */
    private String codeType;

    /**
     * 验证码状态
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
