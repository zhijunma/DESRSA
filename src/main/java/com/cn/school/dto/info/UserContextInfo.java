package com.cn.school.dto.info;

import lombok.Data;

import java.util.List;

/**
 * @author:HuMin Date:2019/3/5
 * Time:10:01
 */
@Data
public class UserContextInfo {
    private static final long serialVersionUID = -5028412161951874201L;

    /**
     * 用户id
     */
    private Long currId;

    /**
     * 用户名
     */
    private String currName;

    /**
     * 用户电话号码
     */
    private String currTel;

    /**
     * 用户角色
     */
    private Integer currRole;

    /**
     * 用户登录授权令牌
     */
    private String token;

    /**
     * 超期时长毫秒
     */
    private Long expiredMillis;

    /**
     * URI
     */
    private List<String> uriList;


}
