
package com.cn.school.dto.forms.auth;

import com.cn.school.dto.info.UserContextInfo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录用户
 *
 * @author:HuMin
 * @Date:2019/1/8
 * @Time:10:45
 */
@Data
public class UserViewForm  extends UserContextViewForm {


    /**
     * 登陆账号
     */
    @NotBlank(message = "登录手机号或密码不能为空")
    private String mobile;

    /**
     * 登录密码
     */
    @NotBlank(message = "登录手机号或密码不能为空")
    private String password;

}
