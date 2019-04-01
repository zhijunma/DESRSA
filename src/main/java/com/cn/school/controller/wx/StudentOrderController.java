package com.cn.school.controller.wx;

import com.cn.school.dto.forms.studentOrder.GetStudentOrderInfoViewForm;
import com.cn.school.dto.forms.usermanage.GetUserViewForm;
import com.cn.school.service.wx.StudentOrderService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: leiyunlong
 * @Date: 2019/4/1 17:09
 * @Version 1.0
 */
@RestController
@RequestMapping("/wx/stuorder")
public class StudentOrderController {
    @Autowired
    private StudentOrderService studentOrderService;

    /**
     * 学院查看个人信息及缴费情况
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/getStudentOrderInfo",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List getStudentOrderInfo(@RequestBody @Validated RestRequest<GetStudentOrderInfoViewForm> request) {
        GetStudentOrderInfoViewForm viewForm = request.getBody();
        return studentOrderService.getStudentOrderInfo(viewForm);
    }

}
