package com.cn.school.service.web;

import com.cn.school.dto.forms.usermanage.AddGroupPurchaseViewForm;
import com.cn.school.dto.forms.usermanage.GroupPurchaseViewForm;
import com.cn.school.utils.response.RestResponse;

import java.util.List;


public interface GroupPurchaseService {

    /**
     * 管理员生成团购活动内容，内容信息只能由管理员添加
     *
     * @param addGroupPurchaseViewForm
     * @return
     */
    RestResponse addGroupPurchase(AddGroupPurchaseViewForm addGroupPurchaseViewForm);

    /**
     * 全部团购活动一览
     *
     * @param groupPurchaseViewForm
     * @return
     */
    List getGroupPurchaseList(GroupPurchaseViewForm groupPurchaseViewForm);

    /**
     * 团购活动详细查看
     *
     * @param groupPurchaseViewForm
     * @return
     */
    RestResponse getGroupPurchase(GroupPurchaseViewForm groupPurchaseViewForm);
}
