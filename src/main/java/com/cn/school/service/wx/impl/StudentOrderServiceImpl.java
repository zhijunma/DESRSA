package com.cn.school.service.wx.impl;

import com.cn.school.dto.forms.studentOrder.GetStudentOrderInfoViewForm;
import com.cn.school.dto.info.vo.GetStudentOrderVO;
import com.cn.school.entity.DSStudentsOrder;
import com.cn.school.mapper.wx.StudentsOrderMapper;
import com.cn.school.service.wx.StudentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
     * 学院查看个人信息及缴费情况
     * @param getStudentOrderInfoViewForm
     * @return
     */
    @Override
    public List getStudentOrderInfo(GetStudentOrderInfoViewForm getStudentOrderInfoViewForm) {
        List<DSStudentsOrder> dsStudentsOrders = studentsOrderMapper.getStudentOrderInfo(getStudentOrderInfoViewForm.getIdCard(),getStudentOrderInfoViewForm.getUserName());
        List<GetStudentOrderVO> getStudentOrderVOS = new ArrayList<>(16);
        dsStudentsOrders.forEach(e -> {
            GetStudentOrderVO getStudentOrderVO =new GetStudentOrderVO();
            getStudentOrderVO.setGuid(e.getGuid());
            getStudentOrderVO.setUserName(e.getUserName());
            getStudentOrderVO.setIdCard(e.getIdCard());
            getStudentOrderVO.setDriverLevel(e.getDriverLevel());
            getStudentOrderVO.setPayable(e.getPayable());
            getStudentOrderVO.setPaid(e.getPaid());
            getStudentOrderVO.setDso_add_time(e.getDso_add_time());
            getStudentOrderVO.setTotalFee(e.getTotalFee());
            getStudentOrderVOS.add(getStudentOrderVO);
        });
        return getStudentOrderVOS;
    }
}
