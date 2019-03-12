package com.cn.school.dto.forms.user;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

/**
 * @author:HuMin Date:2019/3/12
 * Time:11:50
 */
@Data
public class UserInsertViewForm  extends UserContextViewForm {

    private String userName;
}
