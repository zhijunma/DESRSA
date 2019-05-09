package com.cn.school.controller.mzj;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

/**
 * @author:mzj Date:2019/3/8
 * Time:10:50
 */
@Controller
@Api(value="页面测试",description="testController",tags={"跳转index页面"})
@ApiModel(value="页面测试",description="index页面")
public class TestController {
    @GetMapping(value = {"/","/index.html"})
    @ApiOperation(value="跳转到index界面",notes="测试跳转index界面")
    public String index(@ApiParam(name="id",value="用户id",required=true) Long id, @ApiParam(name="username",value="用户名") String username) {
        System.out.println("_________"+LocalDateTime.now() +"__________");
        return "index";
    }

    @GetMapping(value = {"/DES","/DES.html"})
    @ApiOperation(value="跳转到DES界面",notes="测试跳转DES界面")
    public String DES(@ApiParam(name="id",value="用户id",required=true) Long id, @ApiParam(name="username",value="用户名") String username) {
        System.out.println("_________"+LocalDateTime.now() +"__________");
        return "DES";
    }
    @GetMapping(value = {"/RSA","/RSA.html"})
    @ApiOperation(value="跳转到RSA界面",notes="测试跳转RSA界面笔记")
    public String RSA(@ApiParam(name="id",value="用户id",required=true) Long id, @ApiParam(name="username",value="用户名") String username) {
        System.out.println("_________"+LocalDateTime.now() +"__________");
        return "RSA";
    }

}
