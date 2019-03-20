package com.cn.school.service.wx.impl;

import com.cn.school.dto.forms.ComSendCodeViewForm;
import com.cn.school.dto.forms.students.AddStudentsViewForm;
import com.cn.school.entity.DSStudents;
import com.cn.school.mapper.wx.StudentsMapper;
import com.cn.school.service.common.SmsService;
import com.cn.school.service.wx.StudentsService;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author:HuMin Date:2019/2/26
 * Time:10:42
 */
@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    private StudentsMapper studentsMapper;

    @Autowired
    private SmsService smsService;

    /**
     * 学员报名
     *
     * @param viewForm
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public RestResponse addStudents(AddStudentsViewForm viewForm) {

        ComSendCodeViewForm comSendCodeViewForm = new ComSendCodeViewForm();
        comSendCodeViewForm.setCode(viewForm.getCode());
        comSendCodeViewForm.setMobile(viewForm.getMobilePhone());
        //验证验证码
        Boolean flag = smsService.updateCheckMobileCode(comSendCodeViewForm);
        if (!flag) {
            throw new RuntimeException("短信验证错误");
        }

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
        if (num > 0) {
            return RestResponse.success("报名成功");
        } else {
            return RestResponse.error("报名失败");

        }

    }
}
