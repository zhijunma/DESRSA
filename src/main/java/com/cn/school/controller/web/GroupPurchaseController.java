package com.cn.school.controller.web;

import com.cn.school.dto.forms.usermanage.AddGroupPurchaseViewForm;
import com.cn.school.dto.forms.usermanage.DeleteGroupPurchaseViewForm;
import com.cn.school.dto.forms.usermanage.GroupPurchaseViewForm;
import com.cn.school.dto.forms.usermanage.UpdateGroupPurchaseViewForm;
import com.cn.school.service.web.GroupPurchaseService;
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

import java.util.List;

/**
 * @author:HuMin Date:2019/3/4
 * Time:11:02
 */

@RestController
@RequestMapping("/web/groupPurchase")
@Api(description = "PayController", tags = {"团购模块"})
public class GroupPurchaseController {
    @Autowired
    GroupPurchaseService groupPurchaseService;


    /**
     * 管理员生成团购活动内容，内容信息只能由管理员添加
     *
     * @param request
     * @return
     */
    @ApiOperation(value="管理员生成团购活动内容，内容信息只能由管理员添加",notes="")
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
    @ApiOperation(value="全部团购活动一览",notes="")
    @PostMapping(value = "/getGroupPurchaseList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List getGroupPurchaseList(@RequestBody @Validated RestRequest<GroupPurchaseViewForm> request) {
        GroupPurchaseViewForm viewForm = request.getBody();
        return groupPurchaseService.getGroupPurchaseList(viewForm);
    }

    /**
     * 团购活动详细查看
     *
     * @param request
     * @return
     */
    @ApiOperation(value="团购活动详细查看",notes="")
    @PostMapping(value = "/getGroupPurchase")
    public RestResponse getGroupPurchase(@RequestBody @Validated RestRequest<GroupPurchaseViewForm> request) {
        GroupPurchaseViewForm viewForm = request.getBody();
        return groupPurchaseService.getGroupPurchase(viewForm);
    }

    /**
     * 团购信息修改（只能由管理员修改）
     *
     * @param request
     * @return
     */
    @ApiOperation(value="团购信息修改",notes="")
    @PostMapping(value = "/updateGroupPurchase")
    public RestResponse updateGroupPurchase(@RequestBody @Validated RestRequest<UpdateGroupPurchaseViewForm> request) {
        UpdateGroupPurchaseViewForm viewForm = request.getBody();
        return groupPurchaseService.updateGroupPurchase(viewForm);
    }

    /**
     * 团购信息单、批量删除（假删除）
     *
     * @param request
     * @return
     */
    @ApiOperation(value="团购信息单、批量删除（假删除）",notes="")
    @PostMapping(value = "/deleteGroupPurchase")
    public RestResponse deleteGroupPurchase(@RequestBody @Validated RestRequest<DeleteGroupPurchaseViewForm> request) {
        DeleteGroupPurchaseViewForm viewForm = request.getBody();
        return groupPurchaseService.deleteGroupPurchase(viewForm);
    }
}