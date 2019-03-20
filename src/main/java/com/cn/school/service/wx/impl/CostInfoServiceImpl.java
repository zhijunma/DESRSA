package com.cn.school.service.wx.impl;

import com.cn.school.dto.forms.costinfo.GetListViewForm;
import com.cn.school.dto.info.vo.GetCostInfoVO;
import com.cn.school.entity.DSCostInfo;
import com.cn.school.mapper.wx.CostInfoMapper;
import com.cn.school.service.wx.CostInfoService;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:HuMin Date:2019/2/26
 * Time:10:42
 */
@Service
public class CostInfoServiceImpl implements CostInfoService {

    @Autowired
    private CostInfoMapper costInfoMapper;

    /**
     * 学员报名
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse getCostInfoList(GetListViewForm viewForm) {
        List<DSCostInfo> list = costInfoMapper.getCostInfoList();
        List<GetCostInfoVO> infoVOList = new ArrayList<>(16);

        if (!list.isEmpty()) {
            list.forEach(e -> {
                GetCostInfoVO Info = new GetCostInfoVO();
                //驾照登记
                Info.setDriverLevel(e.getDriverLevel());
                //金额
                Info.setDriverMoney(e.getDriverMoney());
                infoVOList.add(Info);
            });
        }
        return RestResponse.success(infoVOList);
    }
}
