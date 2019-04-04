package com.cn.school.controller.wx;

import com.cn.school.dto.forms.studentOrder.GetStudentOrderInfoViewForm;
import com.cn.school.service.wx.StudentOrderService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: leiyunlong
 * @Date: 2019/4/1 17:09
 * @Version 1.0
 */
@RestController
@Api(description = "StudentOrderController", tags = {"学员订单关系接口"})
@RequestMapping("/wx/stuorder")
public class StudentOrderController {
    @Autowired
    private StudentOrderService studentOrderService;

    /**
     * 学员查看个人信息及缴费情况
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "学员查看个人信息及缴费情况", notes = "{\n" +
            "  \"body\": {\n" +
            "    \"openId\": \"000000000000000000000\" \n" +
            "  }\n" +
            "}")
    @PostMapping(value = "/getStudentOrderInfo",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse getStudentOrderInfo(@RequestBody @Validated RestRequest<GetStudentOrderInfoViewForm> request) {
        GetStudentOrderInfoViewForm viewForm = request.getBody();
        return studentOrderService.getStudentOrderInfo(viewForm);
    }

}
