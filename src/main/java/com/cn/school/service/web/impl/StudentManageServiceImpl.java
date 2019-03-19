package com.cn.school.service.web.impl;

import com.cn.school.constant.Constant;
import com.cn.school.dto.forms.usermanage.DeleteStudnetViewForm;
import com.cn.school.dto.forms.usermanage.GetStuViewForm;
import com.cn.school.dto.forms.usermanage.GetStudentViewForm;
import com.cn.school.dto.forms.usermanage.UpdateStudentViewForm;
import com.cn.school.dto.info.vo.GetStuInfoVO;
import com.cn.school.dto.info.vo.GetStudentInfoVO;
import com.cn.school.entity.DSStudents;
import com.cn.school.mapper.web.StudentManageMapper;
import com.cn.school.service.web.StudentManageService;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentManageServiceImpl implements StudentManageService {

    @Autowired
    private StudentManageMapper studentManageMapper;


    /**
     * 学员删除,假删除（更新状态）
     *
     * @param deleteStudnetViewForm
     * @return
     */
    @Override
    public RestResponse deleteStudent(DeleteStudnetViewForm deleteStudnetViewForm) {
        if (Constant.MANAGE_ROLE.equals(deleteStudnetViewForm.getCurrRole())) {
            log.debug("权限不足!");
            return RestResponse.error("权限不足！");
        }
        DSStudents dsStudents = new DSStudents();
        dsStudents.setIdCard(deleteStudnetViewForm.getIdCard());
        dsStudents.setGuid(deleteStudnetViewForm.getGuid());
        dsStudents.setModUserId(deleteStudnetViewForm.getCurrId());
        dsStudents.setModUser(deleteStudnetViewForm.getCurrName());
        dsStudents.setModTime(LocalDateTime.now());
        Integer state = studentManageMapper.deleteStudent(dsStudents);
        if (state > 0) {
            return RestResponse.success("删除学员信息成功！");
        } else {
            return RestResponse.error("删除学员信息失败！");
        }
    }


    /**
     * 管理员修改学员信息
     *
     * @param studentViewForm
     * @return
     */
    @Override
    public RestResponse updateStudent(UpdateStudentViewForm studentViewForm) {
        if (Constant.MANAGE_ROLE.equals(studentViewForm.getCurrRole())) {
            log.debug("权限不足!");
            return RestResponse.error("权限不足！");
        }
        DSStudents dsStudents = new DSStudents();
        dsStudents.setUserName(studentViewForm.getUserName());
        dsStudents.setIdCard(studentViewForm.getIdCard());
        dsStudents.setGuid(studentViewForm.getGuid());
        dsStudents.setMobilePhone(studentViewForm.getMobilePhone());
        dsStudents.setModUserId(studentViewForm.getCurrId());
        dsStudents.setModUser(studentViewForm.getCurrName());
        dsStudents.setModTime(LocalDateTime.now());
        Integer state = studentManageMapper.updateStudent(dsStudents);
        if (state > 0) {
            return RestResponse.success("修改学员信息成功！");
        } else {
            return RestResponse.error("修改学员信息失败！");
        }
    }

    /**
     * 管理员查看学员信息
     *
     * @param studentViewForm
     * @return
     */
    @Override
    public RestResponse getStudent(GetStudentViewForm studentViewForm) {

        DSStudents dsStudents = new DSStudents();
        dsStudents.setIdCard(studentViewForm.getIdCard());
        dsStudents.setGuid(studentViewForm.getGuid());
        dsStudents.setUserName(studentViewForm.getUserName());
        DSStudents student = studentManageMapper.getStudent(dsStudents);

        GetStudentInfoVO getStudentInfoVO = new GetStudentInfoVO();
        getStudentInfoVO.setGuid(student.getGuid());
        getStudentInfoVO.setIdCard(student.getIdCard());
        getStudentInfoVO.setUserName(student.getUserName());
        getStudentInfoVO.setMobilePhone(student.getMobilePhone());
        getStudentInfoVO.setStatus(student.getStatus());
        return RestResponse.success(getStudentInfoVO);
    }

    /**
     * 学员信息一览
     *
     * @param getStuViewForm
     * @return
     * @author leiyunlong
     */
    @Override
    public List getStudentList(GetStuViewForm getStuViewForm) {
        //TODO 添加权限模块 教练员只能查看自己学员，管理员可以查看所有信息
        DSStudents dsStudents = new DSStudents();
        dsStudents.setUserName(getStuViewForm.getUserName());
        dsStudents.setMobilePhone(getStuViewForm.getMobilePhone());
        dsStudents.setIdCard(getStuViewForm.getIdCard());
        dsStudents.setStatus(getStuViewForm.getStatus());
        List<DSStudents> DSStudents = studentManageMapper.getStudentList(dsStudents);
        List<GetStuInfoVO> getStuInfoVOList = new ArrayList<>(16);
        DSStudents.forEach(e -> {
            GetStuInfoVO getStuInfoVO = new GetStuInfoVO();
            getStuInfoVO.setGuid(e.getGuid());
            getStuInfoVO.setUserName(e.getUserName());
            getStuInfoVO.setRole(e.getRole());
            getStuInfoVO.setMobilePhone(e.getMobilePhone());
            getStuInfoVO.setIdCard(e.getIdCard());
            getStuInfoVO.setStatus(e.getStatus());
            getStuInfoVOList.add(getStuInfoVO);
        });
        return getStuInfoVOList;
    }
}




