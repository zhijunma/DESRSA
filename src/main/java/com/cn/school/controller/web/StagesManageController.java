package com.cn.school.controller.web;

import com.cn.school.dto.forms.stagesmanage.AddStagesViewForm;
import com.cn.school.service.web.StagesManageService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:HuMin Date:2019/3/18
 * Time:19:43
 */
@Slf4j
@RestController
public class StagesManageController {

    @Autowired
    private StagesManageService stagesManageService;

    /**
     * 新增分期优惠
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/addStages", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse addStages(@RequestBody @Validated RestRequest<AddStagesViewForm> request) {
        AddStagesViewForm viewForm = request.getBody();
        return stagesManageService.addStages(viewForm);
    }
}
