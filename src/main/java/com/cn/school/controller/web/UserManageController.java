package com.cn.school.controller.web;

import com.cn.school.dto.forms.user.UserInsertViewForm;
import com.cn.school.dto.forms.usermanage.GetUserViewForm;
import com.cn.school.service.web.UsersService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author:HuMin Date:2019/3/4
 * Time:11:02
 */

@RestController
@RequestMapping("/web/user")
public class UserManageController {
    @Autowired
    UsersService usersService;
    /**
     * 添加管理員
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addManager", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse addManager(@RequestBody RestRequest<UserInsertViewForm> request) {
        UserInsertViewForm viewForm = request.getBody();
        return null;
    }

    /**
     * 查看个人信息
     * @param request
     * @return
     */
    @PostMapping(value = "/getUser")
    public RestResponse getUser(@RequestBody @Validated RestRequest<GetUserViewForm> request ){
        GetUserViewForm viewForm = request.getBody();
        return usersService.getUser(viewForm);
    }

    /**
     * 修改个人信息
     * @param request
     * @return
     */
    @PostMapping(value = "/updateUsers")
    public RestResponse updateUsers(@RequestBody @Validated RestRequest<GetUserViewForm> request ){
        GetUserViewForm viewForm = request.getBody();
        return usersService.updateUsers(viewForm);
    }
}