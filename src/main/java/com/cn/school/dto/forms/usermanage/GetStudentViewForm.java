package com.cn.school.dto.forms.usermanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

@Data
public class GetStudentViewForm extends UserContextViewForm {

    /**
     * 主键id
     */
    private Long guid;

    /**
     * 学员名
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
