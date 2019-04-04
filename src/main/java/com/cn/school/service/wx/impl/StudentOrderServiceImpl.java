package com.cn.school.service.wx.impl;

import com.cn.school.dto.forms.studentOrder.GetStudentOrderInfoViewForm;
import com.cn.school.dto.info.vo.GetStudentOrderVO;
import com.cn.school.entity.DSStudents;
import com.cn.school.mapper.wx.StudentsOrderMapper;
import com.cn.school.service.wx.StudentOrderService;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: leiyunlong
 * @Date: 2019/4/1 16:49
 * @Version 1.0
 */
@Service
public class StudentOrderServiceImpl implements StudentOrderService {
    @Autowired
    private StudentsOrderMapper studentsOrderMapper;

    /**
     * 学员查看个人信息及缴费情况
     *
     * @param getStudentOrderInfoViewForm
     * @return
     */
    @Override
    public RestResponse getStudentOrderInfo(GetStudentOrderInfoViewForm getStudentOrderInfoViewForm) {
        DSStudents dsStudents = studentsOrderMapper.getStudentOrderInfo(getStudentOrderInfoViewForm.getOpenId());
        if (ObjectUtils.isEmpty(dsStudents)) {
            return RestResponse.isNull();
        }
        Map<String, Object> map = new HashMap<>(16);

        map.put("guid", dsStudents.getGuid());
        map.put("userName", dsStudents.getUserName());
        map.put("idCard", dsStudents.getIdCard());
        map.put("mobilePhone", dsStudents.getMobilePhone());
        map.put("driverLevel", dsStudents.getDriverLevel());
        map.put("payable", dsStudents.getPayable());
        map.put("paid", dsStudents.getPaid());
        //根据学员id查询缴费情况
        List<GetStudentOrderVO> getStudentOrderVOS = studentsOrderMapper.getStudentOrderByStudentsId(dsStudents.getGuid());
        map.put("stages", getStudentOrderVOS.isEmpty() ? null : getStudentOrderVOS);

        return RestResponse.success(map);
    }
}
