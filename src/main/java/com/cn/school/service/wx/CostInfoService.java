package com.cn.school.service.wx;

import com.cn.school.dto.forms.costinfo.GetListViewForm;
import com.cn.school.utils.response.RestResponse;

/**
 * @author:HuMin Date:2019/3/1
 * Time:16:38
 */
public interface CostInfoService {

    /**
     * 报名费用查询
     *
     * @param viewForm
     * @return
     */
    RestResponse getCostInfoList(GetListViewForm viewForm);
}
