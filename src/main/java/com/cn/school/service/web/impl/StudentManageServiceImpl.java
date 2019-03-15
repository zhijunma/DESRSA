package com.cn.school.service.web.impl;

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
        if (deleteStudnetViewForm.getCurrRole() != 3) {
            log.debug("权限不足!");
            return RestResponse.error("权限不足！");
        }
        DSUser dsUser = new DSUser();
        dsUser.setIdCard(deleteStudnetViewForm.getIdCard());
        dsUser.setGuid(deleteStudnetViewForm.getGuid());
        dsUser.setModUserId(deleteStudnetViewForm.getModUserId());
        dsUser.setModUser(deleteStudnetViewForm.getModUser());
        dsUser.setModTime(LocalDateTime.now());
        Integer state = studentManageMapper.deleteStudent(dsUser);
        System.out.println(dsUser+"++++++++++++++++++++++++++++++++++++");
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
        if (studentViewForm.getCurrRole() != 3) {
            log.debug("权限不足!");
            return RestResponse.error("权限不足！");
        }
        DSUser dsUser = new DSUser();
        dsUser.setUserName(studentViewForm.getUserName());
        dsUser.setIdCard(studentViewForm.getIdCard());
        dsUser.setGuid(studentViewForm.getGuid());
        dsUser.setMobilePhone(studentViewForm.getMobilePhone());
        dsUser.setModUserId(studentViewForm.getModUserId());
        dsUser.setModUser( studentViewForm.getModUser());
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
        //dsUser.setMobilePhone(studentViewForm.getMobilePhone());
        //dsUser.setStatus(studentViewForm.getStatus());
        DSUser student = studentManageMapper.getStudent(dsUser);

        GetStudentInfoVO getStudentInfoVO = new GetStudentInfoVO();
        getStudentInfoVO.setIdCard(student.getIdCard());
        getStudentInfoVO.setGuid(student.getGuid());
        getStudentInfoVO.setUserName(student.getUserName());
        getStudentInfoVO.setMobilePhone(student.getMobilePhone());
        getStudentInfoVO.setStatus(student.getStatus());
        return RestResponse.success(getStudentInfoVO);
    }
}




