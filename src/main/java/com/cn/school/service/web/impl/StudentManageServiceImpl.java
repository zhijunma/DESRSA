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
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

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
        //权限判断
        roleCheck(deleteStudnetViewForm.getCurrRole());
        DSStudents dsStudents = new DSStudents();
        //入参--根据guid进行删除
        dsStudents.setGuid(deleteStudnetViewForm.getGuid());
        //入参--根据学员身份证信息进行删除
        dsStudents.setIdCard(deleteStudnetViewForm.getIdCard());
        //修改人信息
        dsStudents.setModUserId(deleteStudnetViewForm.getCurrId());
        dsStudents.setModUser(deleteStudnetViewForm.getCurrName());
        dsStudents.setModTime(LocalDateTime.now());
        //执行删除操作
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
        //权限判断
        roleCheck(studentViewForm.getCurrRole());
        DSStudents dsStudents = new DSStudents();
        //入参--根据guid来进行修改
        dsStudents.setGuid(studentViewForm.getGuid());
        //入参--学员修改后的姓名
        dsStudents.setUserName(studentViewForm.getUserName());
        //入参--学员修改后的身份证信息
        dsStudents.setIdCard(studentViewForm.getIdCard());
        //入参--学员修改后的电话号码
        dsStudents.setMobilePhone(studentViewForm.getMobilePhone());
        //修改人信息
        dsStudents.setModUserId(studentViewForm.getCurrId());
        dsStudents.setModUser(studentViewForm.getCurrName());
        dsStudents.setModTime(LocalDateTime.now());
        //执行修改操作并返回是否成功
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
        //权限判断
        roleCheck(studentViewForm.getCurrRole());
        DSStudents dsStudents = new DSStudents();
        //查询条件学员guid
        dsStudents.setGuid(studentViewForm.getGuid());
        //查询条件--学员身份证号
        dsStudents.setIdCard(studentViewForm.getIdCard());
        //查询条件--学员姓名
        dsStudents.setUserName(studentViewForm.getUserName());
        DSStudents student = studentManageMapper.getStudent(dsStudents);
        //将查询到的信息缓存到getStudentInfoVO中
        GetStudentInfoVO getStudentInfoVO = new GetStudentInfoVO();
        getStudentInfoVO.setGuid(student.getGuid());
        getStudentInfoVO.setIdCard(student.getIdCard());
        getStudentInfoVO.setUserName(student.getUserName());
        getStudentInfoVO.setMobilePhone(student.getMobilePhone());
        getStudentInfoVO.setStatus(student.getStatus());
        //返回查询到的信息
        return RestResponse.success(getStudentInfoVO);
    }

    /**
     * 学员信息一览
     *
     * @param getStuViewForm
     * @return
     * @author mazhujun
     */
    @Override
    public List getStudentList(GetStuViewForm getStuViewForm) {
        //权限判断
        roleCheck(getStuViewForm.getCurrRole());
        DSStudents dsStudents = new DSStudents();
        //查询条件--电话号码
        dsStudents.setMobilePhone(getStuViewForm.getMobilePhone());
        //查询条件--学员身份证号
        dsStudents.setIdCard(getStuViewForm.getIdCard());
        //查询条件--学员姓名
        dsStudents.setUserName(getStuViewForm.getUserName());
        //获取学员状态，可以根据状态查询
        dsStudents.setStatus(getStuViewForm.getStatus());
        //将查询到的学员信息缓存到List中
        List<DSStudents> DSStudents = studentManageMapper.getStudentList(dsStudents);
        List<GetStuInfoVO> getStuInfoVOList = new ArrayList<>(16);
        //遍历List缓存到getStuInfoVOList中
        DSStudents.forEach(e -> {
            GetStuInfoVO getStuInfoVO = new GetStuInfoVO();
            getStuInfoVO.setGuid(e.getGuid());
            getStuInfoVO.setUserName(e.getUserName());
            getStuInfoVO.setMobilePhone(e.getMobilePhone());
            getStuInfoVO.setIdCard(e.getIdCard());
            getStuInfoVO.setStatus(e.getStatus());
            getStuInfoVOList.add(getStuInfoVO);
        });
        return getStuInfoVOList;
    }
    /**
     * 权限判断
     */
    public void roleCheck(Integer role) {
        if (!Constant.MANAGE_ROLE.equals(role)) {
            if (!Constant.MARKETING_ROLE.equals(role)) {
                log.debug("权限不足!");
                throw new RuntimeException("权限不足！");
            }
        }
    }
}




