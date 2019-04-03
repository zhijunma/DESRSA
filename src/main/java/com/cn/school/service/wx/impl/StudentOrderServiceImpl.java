package com.cn.school.service.wx.impl;

import com.cn.school.dto.forms.studentOrder.GetStudentOrderInfoViewForm;
import com.cn.school.dto.info.vo.GetStudentOrderVO;
import com.cn.school.entity.DSStudentsOrder;
import com.cn.school.mapper.wx.StudentsOrderMapper;
import com.cn.school.service.wx.StudentOrderService;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<DSStudentsOrder> dsStudentsOrders = studentsOrderMapper.getStudentOrderInfo(getStudentOrderInfoViewForm.getIdCard(), getStudentOrderInfoViewForm.getUserName(), getStudentOrderInfoViewForm.getOpenId());
        if (dsStudentsOrders.isEmpty()) {
            return RestResponse.isNull();
        }
        List<GetStudentOrderVO> getStudentOrderVOS = new ArrayList<>(16);
        Map<String, Object> map = new HashMap<>();

        dsStudentsOrders.forEach(e -> {
            GetStudentOrderVO getStudentOrderVO = new GetStudentOrderVO();
            map.put("guid", e.getGuid());
            map.put("userName", e.getUserName());
            map.put("idCard", e.getIdCard());
            map.put("mobilePhone", e.getMobilePhone());
            map.put("driverLevel", e.getDriverLevel());
            map.put("payable", e.getPayable());
            map.put("paid", e.getPaid());
            getStudentOrderVO.setDso_add_time(e.getDso_add_time());
            getStudentOrderVO.setTotalFee(e.getTotalFee());
            getStudentOrderVO.setStatus(e.getStatus());
            getStudentOrderVOS.add(getStudentOrderVO);
            map.put("stages", getStudentOrderVOS);
//            getStudentOrderVO.setGuid(e.getGuid());
//            getStudentOrderVO.setUserName(e.getUserName());
//            getStudentOrderVO.setIdCard(e.getIdCard());
//            getStudentOrderVO.setMobilePhone(e.getMobilePhone());
//            getStudentOrderVO.setDriverLevel(e.getDriverLevel());
//            getStudentOrderVO.setPayable(e.getPayable());
//            getStudentOrderVO.setPaid(e.getPaid());
//
////            map.put("guid",e.getGuid())
//            getStudentOrderVO.setDso_add_time(e.getDso_add_time());
//            getStudentOrderVO.setTotalFee(e.getTotalFee());
        });

        return RestResponse.success(map);
    }
}
