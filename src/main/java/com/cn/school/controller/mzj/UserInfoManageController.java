package com.cn.school.controller.mzj;

import com.cn.school.FormView.GetUserInfoViewForm;
import com.cn.school.entity.mzj.DSUserInfo;
import com.cn.school.service.mzj.UserInfoManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(description = "UserInfoManageController",tags = {"用户信息管理"})
@ApiModel(value="用户信息管理",description="用户信息管理")
@RequestMapping(value = "/UserInfoManage")
public class UserInfoManageController {
    @Autowired
    UserInfoManageService userInfoManageService;

    @PostMapping(value = "/getUserInfo")
    @ApiOperation(value="通过用户guid获取用户信息")
    @ResponseBody
    public DSUserInfo getUserInfo(GetUserInfoViewForm form){
        return userInfoManageService.getUserInfo(form.getGuid());
    }
    @PostMapping(value = "/updateUserInfo")
    @ApiOperation(value="更新用户信息")
    @ResponseBody
    public String updateUserInfo(GetUserInfoViewForm form){
        return userInfoManageService.updateUserInfo(form);
    }
}
