package com.cn.school.dto.forms.studentOrder;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

/**
 * @Author: leiyunlong
 * @Date: 2019/4/1 16:44
 * @Version 1.0
 */
@Data
public class GetStudentOrderInfoViewForm extends UserContextViewForm {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * openId
     */
    private String openId;
}
