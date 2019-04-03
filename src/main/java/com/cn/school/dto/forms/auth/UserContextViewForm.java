package com.cn.school.dto.forms.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:HuMin
 * @Date:2019/1/8
 * @Time:10:45
 */
@Data
public class UserContextViewForm implements Serializable {

    private static final long serialVersionUID = -5028412161951874201L;

    /**
     * 用户ID
     */
    @ApiModelProperty("登录者，用户ID")
    private Long currId;

    /**
     * 用户名
     */
    @ApiModelProperty("登录者，用户名")
    private String currName;

    /**
     * 用户电话号码
     */
    @ApiModelProperty("登录者，用户电话号码")
    private String currTel;

    /**
     * 用户角色
     */
    @ApiModelProperty("登录者，用户角色")
    private Integer currRole;

}
