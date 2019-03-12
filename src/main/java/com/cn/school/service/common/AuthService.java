package com.cn.school.service.common;

import com.cn.school.dto.forms.auth.UserViewForm;
import com.cn.school.dto.info.vo.UserContextVO;
import com.cn.school.utils.response.RestResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:HuMin Date:2019/3/5
 * Time:9:55
 */
public interface AuthService {
    /**
     * 登录
     *
     * @param viewForm
     * @param request
     * @param response
     * @return
     */
    UserContextVO login(UserViewForm restRequest,
                                HttpServletRequest request, HttpServletResponse response);


    /**
     * 注销
     *
     * @param request
     * @param response
     * @return
     */
    RestResponse<String> logout(HttpServletRequest request, HttpServletResponse response);

}
