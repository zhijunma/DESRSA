package com.cn.school.controller.web;

import com.cn.school.dto.forms.stagesmanage.*;
import com.cn.school.service.web.StagesManageService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:HuMin Date:2019/3/18
 * Time:19:43
 */
@Slf4j
@RestController
@RequestMapping("/web/stages")
public class StagesManageController {

    @Autowired
    private StagesManageService stagesManageService;

    /**
     * 新增分期优惠
     *已完成
     * @param request
     * @return
     */
    @PostMapping(value = "/addStages", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse addStages(@RequestBody @Validated RestRequest<AddStagesViewForm> request) {
        AddStagesViewForm viewForm = request.getBody();
        return stagesManageService.addStages(viewForm);
    }

    /**
     * 修改分期优惠
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/updateStages", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse updateStages(@RequestBody @Validated RestRequest<UpStagesViewForm> request) {
        UpStagesViewForm viewForm = request.getBody();
        return stagesManageService.updateStages(viewForm);
    }

    /**
     * 删除分期优惠
     *完成80%
     * @param request
     * @return
     */
    @PostMapping(value = "/deleteStages", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse deleteStages(@RequestBody @Validated RestRequest<StagesViewForm> request) {
        StagesViewForm viewForm = request.getBody();
        return stagesManageService.deleteStages(viewForm);
    }

    /**
     * 启用分期优惠
     *已完成
     * @param request
     * @return
     */
    @PostMapping(value = "/updateStagesEnable", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse updateStagesEnable(@RequestBody @Validated RestRequest<StagesViewForm> request) {
        StagesViewForm viewForm = request.getBody();
        return stagesManageService.updateStagesEnable(viewForm);
    }

    /**
     * 停用分期优惠
     *已完成
     * @param request
     * @return
     */
    @PostMapping(value = "/updateStagesUnEnable", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse updateStagesUnEnable(@RequestBody @Validated RestRequest<StagesViewForm> request) {
        StagesViewForm viewForm = request.getBody();
        return stagesManageService.updateStagesUnEnable(viewForm);
    }

    /**
     * 查询分期优惠List
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/getStagesList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List getStagesList(@RequestBody @Validated RestRequest<GetStagesViewForm> request) {
        GetStagesViewForm viewForm = request.getBody();
        return stagesManageService.getStagesList(viewForm);
    }

    /**
     * 查询分期优惠详情
     *已完成
     * @param request
     * @return
     */
    @PostMapping(value = "/getStagesInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse getStagesInfo(@RequestBody @Validated RestRequest<GetStagesViewForm> request) {
        GetStagesViewForm viewForm = request.getBody();
        return stagesManageService.getStagesInfo(viewForm);
    }
}
