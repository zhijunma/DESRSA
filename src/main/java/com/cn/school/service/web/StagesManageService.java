package com.cn.school.service.web;

import com.cn.school.dto.forms.stagesmanage.*;
import com.cn.school.utils.response.RestResponse;

import java.util.List;

/**
 * @author:mzj Date:2019/3/18
 * Time:19:46
 */
public interface StagesManageService {

    /**
     * 添加分期活动
     *
     * @param viewForm
     * @return
     */
    RestResponse addStages(AddStagesViewForm viewForm);

    /**
     * 删除分期优惠
     *已完成
     * @param viewForm
     * @return
     */
    RestResponse deleteStages(StagesViewForm viewForm);

    /**
     * 修改分期优惠
     *
     * @param viewForm
     * @return
     */
    RestResponse updateStages(UpStagesViewForm viewForm);

    /**
     * 启用分期活动
     *已完成
     * @param viewForm
     * @return
     */
    RestResponse updateStagesEnable(StagesViewForm viewForm);

    /**
     * 停用分期活动
     *已完成
     * @param viewForm
     * @return
     */
    RestResponse updateStagesUnEnable(StagesViewForm viewForm);

    /**
     * 查询分期活动一览
     *
     * @param viewForm
     * @return
     */
    List getStagesList(GetStagesViewForm viewForm);

    /**
     * 查询分期活动详情
     *已完成
     * @param viewForm
     * @return
     */
    RestResponse getStagesInfo(GetStagesInfoViewForm viewForm);
}
