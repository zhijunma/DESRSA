package com.cn.school.dto.info.vo;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

@Data
public class GetCoachInfoVO extends UserContextViewForm {
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
     * 角色   1：学员   2：教练
     */
    private Integer role;
}
