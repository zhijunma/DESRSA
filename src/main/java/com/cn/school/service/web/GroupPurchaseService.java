package com.cn.school.service.web;

import com.cn.school.dto.forms.usermanage.GroupPurchaseViewForm;
import com.cn.school.utils.response.RestResponse;


public interface GroupPurchaseService {

    /**
     * 管理员生成团购活动内容，内容信息只能由管理员添加
     *
     * @param groupPurchaseViewForm
     * @return
     */
    RestResponse addGroupPurchase(GroupPurchaseViewForm groupPurchaseViewForm);
}
