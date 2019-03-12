package com.cn.school.dto.forms;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author:HuMin Date:2019/3/4
 * Time:11:50
 */
@Data
public class ComSendCodeViewForm {
    /**
     * 手机号码
     */
    @NotNull(message = "手机号码不能为空")
    private String mobile;

    /**
     * 验证码
     */
    private String code;
}
