package com.cn.school.service.web.impl;

import com.cn.school.constant.Constant;
import com.cn.school.dto.forms.stagesmanage.AddStagesViewForm;
import com.cn.school.entity.DSstages;
import com.cn.school.mapper.web.StagesManageMapper;
import com.cn.school.service.web.StagesManageService;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author:HuMin Date:2019/3/18
 * Time:19:46
 */
@Service
public class StagesManageServiceImpl implements StagesManageService {
    @Autowired
    private StagesManageMapper stagesManageMapper;

    /**
     * 添加分期活动
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse addStages(AddStagesViewForm viewForm) {
        //权限判断
        if (!Constant.MANAGE_ROLE.equals(viewForm.getCurrRole()) || !Constant.MARKETING_ROLE.equals(viewForm.getCurrRole())) {
            return RestResponse.error("权限不足！");
        }
        DSstages dSstages = new DSstages();
        //入参
        dSstages.setName(viewForm.getName());
        //入参
        dSstages.setIssues(viewForm.getIssues());
        //状态
        dSstages.setStatus(Constant.STATUS_FALSE);

        dSstages.setAddUserId(viewForm.getCurrId());
        dSstages.setAddUser(viewForm.getCurrName());
        dSstages.setAddTime(LocalDateTime.now());
        dSstages.setModUserId(viewForm.getCurrId());
        dSstages.setModUser(viewForm.getCurrName());
        dSstages.setModTime(LocalDateTime.now());
        dSstages.setDeleteFlag(false);
        Integer state = stagesManageMapper.addStages(dSstages);
        if (state > 0) {
            return RestResponse.success("添加教练员信息成功！");
        } else {
            return RestResponse.error("添加教练员信息失败！");
        }


    }
}
