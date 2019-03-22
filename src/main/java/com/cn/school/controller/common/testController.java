package com.cn.school.controller.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author:HuMin Date:2019/3/8
 * Time:10:50
 */
@Controller
@Api(value="测试接口1",description="testController",tags={"测试跳转index接口"})
@ApiModel(value="user对象",description="用户对象user")
public class testController {
    @GetMapping(value = {"/","/index","/login"})
    @ApiOperation(value="跳转到index界面",notes="测试跳转index界面")
    public String sendCode(@ApiParam(name="id",value="用户id",required=true) Long id, @ApiParam(name="username",value="用户名") String username) {
        System.out.println("______________________");
        return "index";
    }



}
