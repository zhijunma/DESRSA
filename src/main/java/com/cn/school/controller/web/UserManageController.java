package com.cn.school.controller.web;

import com.cn.school.dto.forms.user.UserInsertViewForm;
import com.cn.school.dto.forms.usermanage.*;
import com.cn.school.service.web.UsersService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/getUser")
    public RestResponse getUser(@RequestBody @Validated RestRequest<GetUserViewForm> request) {
        GetUserViewForm viewForm = request.getBody();
        return usersService.getUser(viewForm);
    }

    /**
     * 修改个人信息
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/updateUsers")
    public RestResponse updateUsers(@RequestBody @Validated RestRequest<UpdateUserViewForm> request) {
        UpdateUserViewForm viewForm = request.getBody();
        return usersService.updateUsers(viewForm);
    }

    /**
     * 教练员添加，教练员信息只能由管理员添加
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/insertCoach")
    public RestResponse insertCoach(@RequestBody @Validated RestRequest<InsertCoachViewForm> request) {
//        String token=request.getHeader().g;
        InsertCoachViewForm viewForm = request.getBody();
        return usersService.insertCoach(viewForm);
    }

    /**
     * 教练员一览
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/getCoachs")
    public List getCoachs(@RequestBody @Validated RestRequest<GetCoachsViewForm> request) {
        GetCoachsViewForm viewForm = request.getBody();
        return usersService.getCoachs(viewForm);
    }

    /**
     * 教练员删除,假删除（更新状态）
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/deleteCoach")
    public RestResponse deleteCoach(@RequestBody @Validated RestRequest<DeleteCoachViewForm> request) {
        DeleteCoachViewForm viewForm = request.getBody();
        return usersService.deleteCoach(viewForm);
    }

    /**
     * 教练员详情查看（根据身份证号查询教练员）
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/getCoach")
    public RestResponse getCoach(@RequestBody @Validated RestRequest<GetCoachViewForm> request) {
        GetCoachViewForm viewForm = request.getBody();
        return usersService.getCoach(viewForm);
    }

    /**
     * 教练员信息修改（只能由管理员修改）
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/updateCoach")
    public RestResponse updateCoach(@RequestBody @Validated RestRequest<UpdateCoachViewForm> request) {
        UpdateCoachViewForm viewForm = request.getBody();
        return usersService.updateCoach(viewForm);
    }
    /**
     * 学员信息一览
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/getStu")
    public List getStu(@RequestBody @Validated RestRequest<GetStuViewForm> request) {
        GetStuViewForm viewForm = request.getBody();
        return usersService.getStu(viewForm);
    }

    /*@GetMapping("/docker")
    public String index() {
        return "你好,docker";
    }*/

}