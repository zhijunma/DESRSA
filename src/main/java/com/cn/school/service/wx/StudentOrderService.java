package com.cn.school.service.wx;

import com.cn.school.dto.forms.studentOrder.GetStudentOrderInfoViewForm;
import com.cn.school.utils.response.RestResponse;

import java.util.List;

/**
 * @Author: leiyunlong
 * @Date: 2019/4/1 16:42
 * @Version 1.0
 */
public interface StudentOrderService {
    /**
     * 学院查看个人信息及缴费情况
     *
     * @param getStudentOrderInfoViewForm
     * @return
     */
    List getStudentOrderInfo(GetStudentOrderInfoViewForm getStudentOrderInfoViewForm);
}
