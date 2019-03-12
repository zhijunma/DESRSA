package com.cn.school.service.web;

import com.cn.school.dto.forms.usermanage.GetUserViewForm;
import com.cn.school.utils.response.RestResponse;


public interface UsersService {

    /**
     * 查看个人信息
     * @param userViewForm
     * @return
     *
     */
    RestResponse getUser(GetUserViewForm userViewForm);

    /**
     * 修改个人信息
     * @param userViewForm
     * @return
     */
    RestResponse updateUsers(GetUserViewForm userViewForm);
}
