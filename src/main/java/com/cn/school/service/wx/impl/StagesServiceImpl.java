package com.cn.school.service.wx.impl;

import com.cn.school.dto.forms.stages.GetStagesInfoViewForm;
import com.cn.school.dto.forms.stages.GetStagesListViewForm;
import com.cn.school.dto.info.vo.GetStagesInfoByGuidVO;
import com.cn.school.dto.info.vo.GetStagesListByCostVO;
import com.cn.school.entity.DSStages;
import com.cn.school.entity.DSStagesItem;
import com.cn.school.mapper.wx.StagesMapper;
import com.cn.school.service.wx.StagesService;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:HuMin
 */
@Slf4j
@Service
public class StagesServiceImpl implements StagesService {


    @Autowired
    private StagesMapper stagesMapper;

    /**
     * 根据班级查看分期活动
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse getStagesListByCost(GetStagesListViewForm viewForm) {
        List<GetStagesListByCostVO> voList = new ArrayList<>(16);
        List<DSStages> list = stagesMapper.getStagesListByCost(viewForm.getCosId());
        list.forEach(e -> {
            GetStagesListByCostVO getStagesListByCostVO = new GetStagesListByCostVO();
            getStagesListByCostVO.setGuid(e.getGuid());
            getStagesListByCostVO.setCostId(e.getCostId());
            getStagesListByCostVO.setMoney(e.getMoney());
            getStagesListByCostVO.setIssues(e.getIssues());
            getStagesListByCostVO.setName(e.getName());
            voList.add(getStagesListByCostVO);
        });
        return RestResponse.success(voList);
    }

    /**
     * 根据guid查看分期活动详情
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse getStagesInfoByGuid(GetStagesInfoViewForm viewForm) {
        List<GetStagesInfoByGuidVO> voList = new ArrayList<>(16);
        List<DSStagesItem> list = stagesMapper.getStagesInfoByGuid(viewForm.getGuid());
        list.forEach(e -> {
            GetStagesInfoByGuidVO getStagesInfoByGuidVO = new GetStagesInfoByGuidVO();
            getStagesInfoByGuidVO.setGuid(e.getGuid());
            getStagesInfoByGuidVO.setStageGuid(e.getStageGuid());
            getStagesInfoByGuidVO.setRepayAmount(e.getRepayAmount());
            getStagesInfoByGuidVO.setRepayIssue(e.getRepayIssue());
            voList.add(getStagesInfoByGuidVO);
        });
        return RestResponse.success(voList);
    }
}
