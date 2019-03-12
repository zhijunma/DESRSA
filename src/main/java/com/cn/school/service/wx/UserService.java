package com.cn.school.service.wx;

import com.cn.school.dto.forms.WxInsertUserViewForm;
import com.cn.school.utils.Result;

/**
 * @author:HuMin Date:2019/3/1
 * Time:16:38
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param viewForm
     * @return
     */
    Result insertUser(WxInsertUserViewForm viewForm);
}
