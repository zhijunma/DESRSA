package com.cn.school.dto.info.vo;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetStudentInfoVO extends UserContextViewForm {
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
     * 缴费状态  0：未交费 1 ：以缴费
     */
    private Integer status;

}
