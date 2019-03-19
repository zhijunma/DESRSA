package com.cn.school.service.web.impl;

import com.cn.school.constant.Constant;
import com.cn.school.dto.forms.stagesmanage.AddStagesViewForm;
import com.cn.school.dto.forms.stagesmanage.GetStagesViewForm;
import com.cn.school.dto.forms.stagesmanage.StagesViewForm;
import com.cn.school.dto.forms.stagesmanage.UpStagesViewForm;
import com.cn.school.entity.DSstages;
import com.cn.school.mapper.web.StagesManageMapper;
import com.cn.school.service.web.StagesManageService;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author:HuMin Date:2019/3/18
 * Time:19:46
 */
@Slf4j
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
        roleCheck(viewForm.getCurrRole());
        DSstages dSstages = new DSstages();
        //分期活动名称
        dSstages.setName(viewForm.getName());
        //分期活动期次
        dSstages.setIssues(viewForm.getIssues());
        //状态（添加不启用）
        dSstages.setStatus(Constant.STATUS_FALSE);
        //添加人
        dSstages.setAddUserId(viewForm.getCurrId());
        dSstages.setAddUser(viewForm.getCurrName());
        dSstages.setAddTime(LocalDateTime.now());
        //修改人
        dSstages.setModUserId(viewForm.getCurrId());
        dSstages.setModUser(viewForm.getCurrName());
        dSstages.setModTime(LocalDateTime.now());
        dSstages.setDeleteFlag(false);
        Integer state = stagesManageMapper.addStages(dSstages);
        if (state > 0) {
            return RestResponse.success("添加分期信息成功！");
        } else {
            return RestResponse.error("添加分期信息失败！");
        }
    }

    /**
     * 添加分期活动
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse deleteStages(StagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        return RestResponse.error("添加分期信息失败！");

    }

    /**
     * 添加分期活动
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse updateStages(UpStagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        return RestResponse.error("添加分期信息失败！");
    }

    /**
     * 添加分期活动
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse updateStagesEnable(StagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        return RestResponse.error("添加分期信息失败！");

    }

    /**
     * 添加分期活动
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse updateStagesUnEnable(StagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        return RestResponse.error("添加分期信息失败！");

    }

    /**
     * 添加分期活动
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse getStagesList(GetStagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());

        return RestResponse.error("添加分期信息失败！");
    }

    /**
     * 添加分期活动
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse getStagesInfo(StagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        return RestResponse.error("添加分期信息失败！");
    }

    /**
     * 权限判断
     */
    public void roleCheck(Integer role) {
        if (!Constant.MANAGE_ROLE.equals(role) || !Constant.MARKETING_ROLE.equals(role)) {
            log.debug("权限不足!");
            throw new RuntimeException("权限不足！");
        }
    }

}
