package com.cn.school.service.wx.impl;

import com.cn.school.dto.forms.WxInsertUserViewForm;
import com.cn.school.dto.forms.students.AddStudentsViewForm;
import com.cn.school.dto.info.po.InsertUserPO;
import com.cn.school.entity.DSStudents;
import com.cn.school.mapper.wx.StudentsMapper;
import com.cn.school.service.wx.StudentsService;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author:HuMin Date:2019/2/26
 * Time:10:42
 */
@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    private StudentsMapper studentsMapper;
    /**
     * 学员报名
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse addStudents(AddStudentsViewForm viewForm) {
        DSStudents dsStudents = new DSStudents();
        dsStudents.setUserName(viewForm.getUserName());
        dsStudents.setMobilePhone(viewForm.getMobilePhone());
        //身份证
        dsStudents.setIdCard(viewForm.getIdCard());
        //缴费状态
        dsStudents.setStatus(0);
        //删除
        dsStudents.setDeleteFlag(false);
        dsStudents.setAddTime(LocalDateTime.now());
        dsStudents.setAddUser(viewForm.getUserName());
        dsStudents.setAddUserId(0L);
        dsStudents.setModTime(LocalDateTime.now());
        dsStudents.setModUser(viewForm.getUserName());
        dsStudents.setModUserId(0L);
        Integer num = studentsMapper.addStudents(dsStudents);
        return RestResponse.success("新增成功");
    }
}
