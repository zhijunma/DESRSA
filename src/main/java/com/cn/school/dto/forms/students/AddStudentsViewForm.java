package com.cn.school.dto.forms.students;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * @author:HuMin Date:2019/3/19
 * Time:11:55
 */
@Data
@ApiModel(value = "报名注册请求",description = "AddStudentsViewForm")
public class AddStudentsViewForm {
    /**
     * openId
     */
    @NotBlank(message = "openId不能为空")
    @ApiModelProperty(value = "openId")
    private String openId;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
     * 电话号码
     */
    @NotBlank(message = "电话号码不能为空")
    @ApiModelProperty(value = "电话号码")
    @Size(min = 11, max = 11, message = "输入正确的电话号码")
    private String mobilePhone;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码")
    @Size(min = 6, max = 6, message = "输入正确的验证码")
    private String code;
    /**
     * 身份证号
     */
    @NotBlank(message = "身份证号码不能为空")
    @ApiModelProperty(value = "身份证号码")
    @Size(min = 18, max = 18, message = "输入正确的身份证号码")
    private String idCard;

    /**
     * 应缴费
     */
    @ApiModelProperty(value = "应缴费")
    private BigDecimal payable;

    /**
     * 已缴费
     */
    @ApiModelProperty(value = "已缴费")
    private BigDecimal paid;

    /**
     * 驾照等级
     */
    @ApiModelProperty(value = "驾照等级")
    private String driverLevel;

    /**
     * 分期id
     */
    @ApiModelProperty(value = "分期id")
    private Long stagesGuid;
}
