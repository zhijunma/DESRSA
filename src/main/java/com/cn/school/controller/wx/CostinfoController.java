package com.cn.school.controller.wx;

import com.cn.school.dto.forms.costinfo.GetListViewForm;
import com.cn.school.service.wx.CostInfoService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 *
 * @author:HuMin Date:2019/3/1
 * Time:15:25
 */
@Controller
@RequestMapping("/wx/cost")
public class CostinfoController {

    @Autowired
    private CostInfoService costInfoService;

    /**
     * 报名费用查询
     *
     * @return
     */
    @PostMapping(value = "/getCostInfoList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public RestResponse getCostInfoList(@Validated @RequestBody RestRequest<GetListViewForm> request) {
        GetListViewForm viewForm = request.getBody();
        return costInfoService.getCostInfoList(viewForm);
    }



}
