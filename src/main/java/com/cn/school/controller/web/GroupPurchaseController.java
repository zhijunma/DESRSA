package com.cn.school.controller.web;

import com.cn.school.dto.forms.usermanage.AddGroupPurchaseViewForm;
import com.cn.school.dto.forms.usermanage.GroupPurchaseViewForm;
import com.cn.school.service.web.GroupPurchaseService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RequestMapping("/web/groupPurchase")
public class GroupPurchaseController {
    @Autowired
    GroupPurchaseService groupPurchaseService;


    /**
     * 教练员添加，教练员信息只能由管理员添加
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/addGroupPurchase", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse addGroupPurchase(@RequestBody @Validated RestRequest<AddGroupPurchaseViewForm> request) {
        AddGroupPurchaseViewForm viewForm = request.getBody();
        return groupPurchaseService.addGroupPurchase(viewForm);
    }

    /**
     * 全部团购活动一览
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/getGroupPurchaseList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List getCoachList(@RequestBody @Validated RestRequest<GroupPurchaseViewForm> request) {
        GroupPurchaseViewForm viewForm = request.getBody();
        return groupPurchaseService.getGroupPurchaseList(viewForm);
    }
    @PostMapping(value = "/getGroupPurchase")
    public RestResponse getGroupPurchase(@RequestBody @Validated RestRequest<GroupPurchaseViewForm> request) {
        GroupPurchaseViewForm viewForm = request.getBody();
        return groupPurchaseService.getGroupPurchase(viewForm);
    }

}