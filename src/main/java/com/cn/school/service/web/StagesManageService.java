package com.cn.school.service.web;

import com.cn.school.dto.forms.stagesmanage.AddStagesViewForm;
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
}
