package com.cn.school.controller.web;

import com.cn.school.dto.forms.usermanage.DeleteStudnetViewForm;
import com.cn.school.dto.forms.usermanage.GetStuViewForm;
import com.cn.school.dto.forms.usermanage.GetStudentViewForm;
import com.cn.school.dto.forms.usermanage.UpdateStudentViewForm;
import com.cn.school.service.web.StudentManageService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:HuMin Date:2019/3/4
 * Time:11:02
 */

@RestController
@RequestMapping("/web/studentManage")
public class StudentManageController {

    @Autowired
    StudentManageService studentManageService;


    /**
     * 教练员删除,假删除（更新状态）
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/deleteStudent")
    public RestResponse deleteStudent(@RequestBody @Validated RestRequest<DeleteStudnetViewForm> request) {
        DeleteStudnetViewForm viewForm = request.getBody();
        return studentManageService.deleteStudent(viewForm);
    }

    /**
     * 修改学员信息
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/updateStudent")
    public RestResponse updateStudent(@RequestBody @Validated RestRequest<UpdateStudentViewForm> request) {
        UpdateStudentViewForm viewForm = request.getBody();
        return studentManageService.updateStudent(viewForm);
    }

    /**
     * 查看学员信息
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/getStudent")
    public RestResponse getStudent(@RequestBody @Validated RestRequest<GetStudentViewForm> request) {
        GetStudentViewForm viewForm = request.getBody();
        return studentManageService.getStudent(viewForm);
    }

    /**
     * 学员信息一览
     *
     * @param request
     * @return
     * @author leiyunlong
     */
    @PostMapping(value = "/getStudentList")
    public List getStudentList(@RequestBody @Validated RestRequest<GetStuViewForm> request) {
        GetStuViewForm viewForm = request.getBody();
        return studentManageService.getStudentList(viewForm);
    }
}