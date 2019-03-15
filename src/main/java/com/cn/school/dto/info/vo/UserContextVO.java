package com.cn.school.dto.info.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author:HuMin Date:2019/3/4
 * Time:16:18
 */
@Data
public class UserContextVO implements Serializable {
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

}
