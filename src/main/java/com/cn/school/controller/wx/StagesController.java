package com.cn.school.controller.wx;

import com.cn.school.dto.forms.stages.GetStagesInfoViewForm;
import com.cn.school.dto.forms.stages.GetStagesListViewForm;
import com.cn.school.service.wx.StagesService;
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
    private StagesService stagesService;

    /**
     * 根据班级查看分期活动
     *
     * @return
     */
    @PostMapping(value = "/getStagesListByCost")
    @ResponseBody
    public RestResponse getStagesListByCost(@Validated @RequestBody RestRequest<GetStagesListViewForm> request) {
        GetStagesListViewForm viewForm = request.getBody();
        return stagesService.getStagesListByCost(viewForm);
    }

    /**
     * 根据guid查看分期活动详情
     *
     * @return
     */
    @PostMapping(value = "/getStagesInfoByGuid")
    @ResponseBody
    public RestResponse getStagesInfoByGuid(@Validated @RequestBody RestRequest<GetStagesInfoViewForm> request) {
        GetStagesInfoViewForm viewForm = request.getBody();
        return stagesService.getStagesInfoByGuid(viewForm);
    }
}
