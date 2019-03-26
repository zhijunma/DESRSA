package com.cn.school.controller.web;

import com.cn.school.dto.forms.costinfomanage.AddCostManageViewForm;
import com.cn.school.dto.forms.costinfomanage.DelCostManageViewForm;
import com.cn.school.dto.forms.costinfomanage.GetCostManageListViewForm;
import com.cn.school.dto.forms.costinfomanage.UpCostManageViewForm;
import com.cn.school.service.web.CostInfoManageService;
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
 * 报名
 *
 * @author:HuMin Date:2019/3/1
 * Time:15:25
 */
@Controller
@RequestMapping("/web/cost")
public class CostinfoManageController {

    @Autowired
    private CostInfoManageService costInfoManageService;

    /**
     * 获取报名费用list
     *
     * @return
     */
    @PostMapping(value = "/getCostInfoList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public RestResponse getCostInfoList(@Validated @RequestBody RestRequest<GetCostManageListViewForm> request) {
        GetCostManageListViewForm viewForm = request.getBody();
        return costInfoManageService.getCostInfoList(viewForm);
    }

    /**
     * 新增报名费用
     *
     * @return
     */
    @PostMapping(value = "/addCostInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public RestResponse addCostInfo(@Validated @RequestBody RestRequest<AddCostManageViewForm> request) {
        AddCostManageViewForm viewForm = request.getBody();
        return costInfoManageService.addCostInfo(viewForm);
    }

    /**
     * 修改报名费用
     *
     * @return
     */
    @PostMapping(value = "/updateCostInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public RestResponse updateCostInfo(@Validated @RequestBody RestRequest<UpCostManageViewForm> request) {
        UpCostManageViewForm viewForm = request.getBody();
        return costInfoManageService.updateCostInfo(viewForm);
    }

    /**
     * 删除报名费用
     *
     * @return
     */
    @PostMapping(value = "/deleteCostInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public RestResponse deleteCostInfo(@Validated @RequestBody RestRequest<DelCostManageViewForm> request) {
        DelCostManageViewForm viewForm = request.getBody();
        return costInfoManageService.deleteCostInfo(viewForm);
    }


}
