package com.cn.school.controller.web;

import com.cn.school.dto.forms.EvaluateManage.AddEvaluateViewForm;
import com.cn.school.dto.forms.EvaluateManage.DeleteEvaluateViewForm;
import com.cn.school.dto.forms.EvaluateManage.GetEvaluateViewForm;
import com.cn.school.service.web.EvaluateManageService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:HuMin Date:2019/3/4
 * Time:11:02
 */

@RestController
@RequestMapping("/web/evaluate")
public class EvaluateManageController {

    @Autowired
    EvaluateManageService evaluateManageService;


    /**
     * 管理员删除评价与投诉，假删除（更新状态）
     * @param request
     * @return
     */
    @PostMapping(value = "/deleteEvaluate")
    public RestResponse deleteEvaluate(@RequestBody @Validated RestRequest <DeleteEvaluateViewForm> request) {
        DeleteEvaluateViewForm viewForm = request.getBody();
        return evaluateManageService.deleteEvaluate(viewForm);
    }

    /**
     * 管理员一览评价与投诉
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/getEvaluates")
    public RestResponse getEvaluates(@RequestBody @Validated RestRequest<GetEvaluateViewForm> request) {
        GetEvaluateViewForm viewForm = request.getBody();
        return evaluateManageService.getEvaluates(viewForm);
    }
    /**
     * 管理员查看评价与投诉详细
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/getEvaluate")
    public RestResponse getEvaluate(@RequestBody @Validated RestRequest<GetEvaluateViewForm> request) {
        GetEvaluateViewForm viewForm = request.getBody();
        return evaluateManageService.getEvaluate(viewForm);
    }
    /**
     * 学员添加评价与投诉
     * @param request
     * @return
     */
    @PostMapping(value = "/addEvaluate")
    public RestResponse addEvaluate(@RequestBody @Validated RestRequest <AddEvaluateViewForm> request) {
        AddEvaluateViewForm viewForm = request.getBody();
        return evaluateManageService.addEvaluate(viewForm);
    }
    /**
     * 学员一览评价与投诉
     *
     * @return
     */
    @PostMapping(value = "/stuGetEvaluates")
    public RestResponse stuGetEvaluates() {
        return evaluateManageService.stuGetEvaluates();
    }

}