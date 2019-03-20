package com.cn.school.service.wx;

import com.cn.school.dto.forms.costinfo.GetListViewForm;
import com.cn.school.utils.response.RestResponse;

/**
 * @author:HuMin Date:2019/3/1
 * Time:16:38
 */
public interface CostInfoService {

    /**
     * 学员报名
     *
     * @param viewForm
     * @return
     */
    RestResponse getCostInfoList(GetListViewForm viewForm);
}
