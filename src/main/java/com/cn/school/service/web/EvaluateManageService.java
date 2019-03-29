package com.cn.school.service.web;

import com.cn.school.dto.forms.EvaluateManage.AddEvaluateViewForm;
import com.cn.school.dto.forms.EvaluateManage.DeleteEvaluateViewForm;
import com.cn.school.dto.forms.EvaluateManage.GetEvaluateViewForm;
import com.cn.school.utils.response.RestResponse;

import java.util.List;


public interface EvaluateManageService {

    /**
     * 管理员删除评价与投诉,假删除（更新状态）
     *
     * @param deleteEvaluateViewForm
     * @return
     */
    RestResponse deleteEvaluate(DeleteEvaluateViewForm deleteEvaluateViewForm);

    /**
     * 管理员一览评价与投诉
     *
     * @param getEvaluateViewForm
     * @return
     */
    RestResponse getEvaluates(GetEvaluateViewForm getEvaluateViewForm);
    /**
     * 管理员一览评价与投诉
     *
     * @param getEvaluateViewForm
     * @return
     */
    RestResponse getEvaluate(GetEvaluateViewForm getEvaluateViewForm);

    /**
     * 学员添加评价与投诉
     * @param addEvaluateViewForm
     * @return
     */
    RestResponse addEvaluate(AddEvaluateViewForm addEvaluateViewForm);

    /**
     * 学员查看评价与投诉
     * @return
     */
    RestResponse stuGetEvaluates();

}
