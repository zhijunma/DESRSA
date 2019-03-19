package com.cn.school.service.web;

import com.cn.school.dto.forms.usermanage.DeleteStudnetViewForm;
import com.cn.school.dto.forms.usermanage.GetStuViewForm;
import com.cn.school.dto.forms.usermanage.GetStudentViewForm;
import com.cn.school.dto.forms.usermanage.UpdateStudentViewForm;
import com.cn.school.utils.response.RestResponse;

import java.util.List;

public interface StudentManageService {


    /**
     * 学员删除,假删除（更新状态）
     *
     * @param deleteStudnetViewForm
     * @return
     */
    RestResponse deleteStudent(DeleteStudnetViewForm deleteStudnetViewForm);


    /**
     * 管理员修改学员信息
     *
     * @param updateStudentViewForm
     * @return
     */
    RestResponse updateStudent(UpdateStudentViewForm updateStudentViewForm);

    /**
     * 管理员查看学员信息
     *
     * @param getStudentViewForm
     * @return
     */
    RestResponse getStudent(GetStudentViewForm getStudentViewForm);

    /**
     * 学员信息一览
     *
     * @author leiyunloing
     * @param getStuViewForm
     * @return
     */
    List getStudentList(GetStuViewForm getStuViewForm);
}


