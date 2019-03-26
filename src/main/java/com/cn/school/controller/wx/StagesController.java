package com.cn.school.controller.wx;

import com.cn.school.dto.forms.stagesmanage.GetStagesViewForm;
import com.cn.school.service.web.StagesManageService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:mzj 2019/3/26
 * date:9:20
 */

@Controller
@RequestMapping("/wx/stages")
public class StagesController {
    @Autowired
    private StagesManageService stagesManageService;

    /**
     * 选择分期（查看分期详情）
     * @return
     */
    @PostMapping(value = "/getStages")
    @ResponseBody
    public RestResponse register(@Validated @RequestBody RestRequest<GetStagesViewForm> request) {
        GetStagesViewForm viewForm = request.getBody();
        return stagesManageService.getStagesInfo(viewForm);
    }
}
