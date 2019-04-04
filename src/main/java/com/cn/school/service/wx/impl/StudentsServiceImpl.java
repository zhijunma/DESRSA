package com.cn.school.service.wx.impl;

import com.cn.school.dto.forms.ComSendCodeViewForm;
import com.cn.school.dto.forms.students.AddInfoViewForm;
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

    public static void main(String[] args) {

    }
    /**
     * 学员报名
     *
     * @param viewForm
     * @return
     */

    @Override
    @Transactional(rollbackFor = RuntimeException.class, timeout = 30)
    public RestResponse addStudents(AddStudentsViewForm viewForm) {

        ComSendCodeViewForm comSendCodeViewForm = new ComSendCodeViewForm();
        comSendCodeViewForm.setCode(viewForm.getCode());
        comSendCodeViewForm.setMobile(viewForm.getMobilePhone());
        Integer count = studentsMapper.getMobileOnly(viewForm.getMobilePhone(),viewForm.getOpenId(),viewForm.getIdCard());
        if (count > 0) {
            throw new RuntimeException("该号码/身份证号/微信号已经报名成功，请移步个人信息查看！");
        }
        //验证验证码
        Boolean flag = smsService.updateCheckMobileCode(comSendCodeViewForm);
        if (!flag) {
            throw new RuntimeException("短信验证错误");
        }

        DSStudents dsStudents = new DSStudents();
        dsStudents.setUserName(viewForm.getUserName());
        //openid 微信唯一标识
        dsStudents.setOpenId(viewForm.getOpenId());
        dsStudents.setMobilePhone(viewForm.getMobilePhone());
        //身份证
        dsStudents.setIdCard(viewForm.getIdCard());
        //应缴总额
        dsStudents.setPayable(viewForm.getPayable());
        //驾照等级
        dsStudents.setDriverLevel(viewForm.getDriverLevel());
        //缴费状态
        dsStudents.setStatus(0);
        //分期id
        dsStudents.setStagesGuid(viewForm.getStagesGuid());
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
            return RestResponse.success(dsStudents.getGuid());
        } else {
            throw new RuntimeException("报名失败");

        }

    }

    /**
     * 添加报名信息
     *
     * @param viewForm
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class, timeout = 30)
    public RestResponse addInfo(AddInfoViewForm viewForm) {

        DSStudents dsStudents = new DSStudents();
        dsStudents.setDriverLevel(viewForm.getDriverLevel());
        dsStudents.setPayable(viewForm.getPayable());
        dsStudents.setStagesGuid(viewForm.getStagesGuid());
        dsStudents.setModTime(LocalDateTime.now());
        dsStudents.setModUser("self");
        dsStudents.setModUserId(0L);
        Integer num = studentsMapper.updateStudentInfo(dsStudents);
        if (num > 0) {
            return RestResponse.success("添加成功");
        } else {
            throw new RuntimeException("添加失败");

        }

    }
}
