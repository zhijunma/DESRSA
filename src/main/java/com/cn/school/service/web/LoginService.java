package com.cn.school.service.web;

import com.cn.school.dto.forms.auth.UserViewForm;
import com.cn.school.utils.response.RestResponse;

/**
 * @author:HuMin Date:2019/3/12
 * Time:13:05
 */
public interface LoginService {
    /**
     * 登录
     * @param viewForm
     * @return
     */
    RestResponse login(UserViewForm viewForm);
}
