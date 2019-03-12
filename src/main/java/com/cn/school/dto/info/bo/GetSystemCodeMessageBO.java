package com.cn.school.dto.info.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * 发送验证码消息
 *
 * @author:HuMin Date:2019/3/4
 * Time:14:46
 */
@Data
public class GetSystemCodeMessageBO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -169605420830847345L;

    @NotNull(message = "发送参数不能为空")
    private String  code;

    @NotNull(message = "模板代码不能为空")
    private String tempCode;

    @NotBlank(message = "手机号不能为空")
    private String mobile;
}
