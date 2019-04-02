package com.cn.school.dto.forms.pay;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class InitPayViewForm {
    private String method;
    /**
     * 商品描述
     */
    @NotBlank(message="商品描述不能为空")
    private String body;
    /**
     * 用户openid
     */
    @NotBlank(message="用户openid不能为空")
    private String sub_openid;
    /**
     * 总金额
     */
    @NotNull(message="总金额不能为空")
    private Integer total_fee;
    /**
     * L传报名的学生guid
     */
    @NotNull(message="学生studentsGuid不能为空")
    private Long studentsGuid;

}
