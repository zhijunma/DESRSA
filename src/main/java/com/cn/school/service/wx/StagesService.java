package com.cn.school.service.wx;

import com.cn.school.dto.forms.stages.GetStagesInfoViewForm;
import com.cn.school.dto.forms.stages.GetStagesListViewForm;
import com.cn.school.utils.response.RestResponse;

/**
 * @author:mzj Date:2019/3/18
 * Time:19:46
 */
public interface StagesService {
    /**
     * 根据班级查看分期活动
     * 已完成
     *
     * @param viewForm
     * @return
     */
    RestResponse getStagesListByCost(GetStagesListViewForm viewForm);

    /**
     * 根据guid查看分期活动详情
     * 已完成
     *
     * @param viewForm
     * @return
     */
    RestResponse getStagesInfoByGuid(GetStagesInfoViewForm viewForm);
}
