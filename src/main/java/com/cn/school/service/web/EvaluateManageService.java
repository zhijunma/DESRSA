package com.cn.school.service.web;

import com.cn.school.dto.forms.EvaluateManage.DeleteEvaluateViewForm;
import com.cn.school.dto.forms.EvaluateManage.GetEvaluateViewForm;
import com.cn.school.utils.response.RestResponse;

import java.util.List;


public interface EvaluateManageService {

    /**
     * 学员删除,假删除（更新状态）
     *
     * @param deleteEvaluateViewForm
     * @return
     */
    RestResponse deleteEvaluate(DeleteEvaluateViewForm deleteEvaluateViewForm);

    /**
     * 管理员查看学员信息
     *
     * @param getEvaluateViewForm
     * @return
     */
    List getEvaluates(GetEvaluateViewForm getEvaluateViewForm);

}
