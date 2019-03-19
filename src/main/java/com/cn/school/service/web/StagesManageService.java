package com.cn.school.service.web;

import com.cn.school.dto.forms.stagesmanage.AddStagesViewForm;
import com.cn.school.dto.forms.stagesmanage.GetStagesViewForm;
import com.cn.school.dto.forms.stagesmanage.StagesViewForm;
import com.cn.school.dto.forms.stagesmanage.UpStagesViewForm;
import com.cn.school.utils.response.RestResponse;

/**
 * @author:HuMin Date:2019/3/18
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
     *
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
     *
     * @param viewForm
     * @return
     */
    RestResponse updateStagesEnable(StagesViewForm viewForm);

    /**
     * 停用分期活动
     *
     * @param viewForm
     * @return
     */
    RestResponse updateStagesUnEnable(StagesViewForm viewForm);

    /**
     * 查询分期活动List
     *
     * @param viewForm
     * @return
     */
    RestResponse getStagesList(GetStagesViewForm viewForm);

    /**
     * 查询分期活动详情
     *
     * @param viewForm
     * @return
     */
    RestResponse getStagesInfo(StagesViewForm viewForm);
}
