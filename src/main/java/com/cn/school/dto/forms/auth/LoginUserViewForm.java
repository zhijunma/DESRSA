
package com.cn.school.dto.forms.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 登录用户信息
 * <p>
 *
 * @author:HuMin
 * @Date:2019/1/8
 * @Time:10:45
 */


@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class LoginUserViewForm extends UserContextViewForm {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4911946411463635589L;
    /**
     * 授权令牌
     */
    @NotNull(message = "授权无效")
    private String token;
}
