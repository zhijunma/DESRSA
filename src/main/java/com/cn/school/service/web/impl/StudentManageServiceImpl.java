package com.cn.school.service.web.impl;

import com.cn.school.constant.Constant;
import com.cn.school.dto.forms.usermanage.DeleteStudnetViewForm;
import com.cn.school.dto.forms.usermanage.GetStudentViewForm;
import com.cn.school.dto.forms.usermanage.UpdateStudentViewForm;
import com.cn.school.dto.info.vo.GetStudentInfoVO;
import com.cn.school.entity.DSUser;
import com.cn.school.mapper.web.StudentManageMapper;
import com.cn.school.service.web.StudentManageService;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        DSUser dsUser = new DSUser();
        dsUser.setIdCard(deleteStudnetViewForm.getIdCard());
        dsUser.setGuid(deleteStudnetViewForm.getGuid());
        dsUser.setModUserId(deleteStudnetViewForm.getCurrId());
        dsUser.setModUser(deleteStudnetViewForm.getCurrName());
        dsUser.setModTime(LocalDateTime.now());
        Integer state = studentManageMapper.deleteStudent(dsUser);
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
        DSUser dsUser = new DSUser();
        dsUser.setUserName(studentViewForm.getUserName());
        dsUser.setIdCard(studentViewForm.getIdCard());
        dsUser.setGuid(studentViewForm.getGuid());
        dsUser.setMobilePhone(studentViewForm.getMobilePhone());
        dsUser.setModUserId(studentViewForm.getCurrId());
        dsUser.setModUser(studentViewForm.getCurrName());
        dsUser.setModTime(LocalDateTime.now());
        Integer state = studentManageMapper.updateStudent(dsUser);
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

        DSUser dsUser = new DSUser();
        dsUser.setIdCard(studentViewForm.getIdCard());
        dsUser.setGuid(studentViewForm.getGuid());
        dsUser.setUserName(studentViewForm.getUserName());
        DSUser student = studentManageMapper.getStudent(dsUser);

        GetStudentInfoVO getStudentInfoVO = new GetStudentInfoVO();
        getStudentInfoVO.setGuid(student.getGuid());
        getStudentInfoVO.setIdCard(student.getIdCard());
        getStudentInfoVO.setUserName(student.getUserName());
        getStudentInfoVO.setMobilePhone(student.getMobilePhone());
        getStudentInfoVO.setStatus(student.getStatus());
        return RestResponse.success(getStudentInfoVO);
    }
}




