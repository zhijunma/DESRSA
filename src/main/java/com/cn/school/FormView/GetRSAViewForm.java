package com.cn.school.FormView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel()
public class GetRSAViewForm {
    /**
     * id
     */
    @ApiModelProperty(value = "主键id",hidden = true)
    private Long id;
    /**
     * id
     */
    @ApiModelProperty(value = "RSA密文id",example = "13")
    private Long rsaId;
    /**
     * password
     */
    @ApiModelProperty(value = "RSA密文")
    private String password;
    /**
     * priveteKey
     */
    @ApiModelProperty(value = "RSA私钥")
    private String privateKey;
}
