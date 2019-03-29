package com.cn.school.service.wx;

import com.cn.school.dto.forms.students.AddInfoViewForm;
import com.cn.school.dto.forms.students.AddStudentsViewForm;
import com.cn.school.utils.response.RestResponse;

/**
 * @author:HuMin Date:2019/3/1
 * Time:16:38
 */
public interface StudentsService {

    /**
     * 学员报名
     *
     * @param viewForm
     * @return
     */
    RestResponse addStudents(AddStudentsViewForm viewForm);

    /**
     * 添加报名信息
     *
     * @param viewForm
     * @return
     */
    RestResponse addInfo(AddInfoViewForm viewForm);
}
