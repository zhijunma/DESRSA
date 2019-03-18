package com.cn.school.controller.web;

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
     * 评价删除,假删除（更新状态）
     * @param request
     * @return
     */
    @PostMapping(value = "/deleteEvaluate")
    public RestResponse deleteEvaluate(@RequestBody @Validated RestRequest <DeleteEvaluateViewForm> request) {
        DeleteEvaluateViewForm viewForm = request.getBody();
        return evaluateManageService.deleteEvaluate(viewForm);
    }

    /**
     * 评论信息一览
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/getEvaluates")
    public List getEvaluates(@RequestBody @Validated RestRequest<GetEvaluateViewForm> request) {
        GetEvaluateViewForm viewForm = request.getBody();
        return evaluateManageService.getEvaluates(viewForm);
    }


}