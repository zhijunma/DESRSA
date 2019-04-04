package com.cn.school.dto.forms.studentOrder;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: leiyunlong
 * @Date: 2019/4/1 16:44
 * @Version 1.0
 */
@Data
public class GetStudentOrderInfoViewForm extends UserContextViewForm {
    /**
     * openId
     */
    @NotBlank(message = "openId不能为空")
    private String openId;
}
