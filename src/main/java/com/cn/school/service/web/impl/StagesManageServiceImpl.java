package com.cn.school.service.web.impl;

import com.cn.school.constant.Constant;
import com.cn.school.dto.forms.stagesmanage.*;
import com.cn.school.dto.info.vo.GetStagesInfoVO;
import com.cn.school.entity.DSStages;
import com.cn.school.entity.DSStagesItem;
import com.cn.school.mapper.web.StagesManageMapper;
import com.cn.school.service.web.StagesManageService;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:
 */
@Slf4j
@Service
public class StagesManageServiceImpl implements StagesManageService {
    @Autowired
    private StagesManageMapper stagesManageMapper;

    /**
     * 添加分期活动
     * 已完成
     *
     * @param viewForm
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class, timeout = 30)
    public RestResponse addStages(AddStagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        //主表
        DSStages dsStages = new DSStages();
        //分期活动名称
        dsStages.setName(viewForm.getName());
        //分期活动期次
        dsStages.setIssues(viewForm.getIssues());
        //状态（添加不启用）
        dsStages.setStatus(Constant.STATUS_FALSE);
        //添加人
        dsStages.setAddUserId(viewForm.getCurrId());
        dsStages.setAddUser(viewForm.getCurrName());
        dsStages.setAddTime(LocalDateTime.now());
        //修改人
        dsStages.setModUserId(viewForm.getCurrId());
        dsStages.setModUser(viewForm.getCurrName());
        dsStages.setModTime(LocalDateTime.now());
        dsStages.setDeleteFlag(false);
        //插入信息到分期优惠表主表
        Long st = stagesManageMapper.addStages(dsStages);
        if (st <= 0) {
            throw new RuntimeException("添加分期信息失败！");
        }
        List<DSStagesItem> itemList = new ArrayList<>(16);
        List<AddStagesItemViewForm> itemViewFormList = viewForm.getItemList();
        //遍历到list中
        itemViewFormList.forEach(e -> {
            DSStagesItem dsStagesItem = new DSStagesItem();
            dsStagesItem.setStageGuid(dsStages.getGuid());
            //还款期次
            dsStagesItem.setRepayIssue(e.getRepayIssue());
            //还款金额
            dsStagesItem.setRepayAmount(e.getRepayAmount());
            //添加人
            dsStagesItem.setAddUserId(viewForm.getCurrId());
            dsStagesItem.setAddUser(viewForm.getCurrName());
            dsStagesItem.setAddTime(LocalDateTime.now());
            //修改人
            dsStagesItem.setModUserId(viewForm.getCurrId());
            dsStagesItem.setModUser(viewForm.getCurrName());
            dsStagesItem.setModTime(LocalDateTime.now());
            dsStagesItem.setDeleteFlag(false);
            itemList.add(dsStagesItem);
        });
        Integer num = stagesManageMapper.addStagesItem(itemList);
        if (num > 0) {
            return RestResponse.success("添加分期信息成功！");
        } else {
            throw new RuntimeException("添加分期信息失败！");
        }
    }

    /**
     * 删除分期活动
     * 已完成
     *
     * @param viewForm
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class, timeout = 30)
    public RestResponse deleteStages(StagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        //入参 分期活动的guidList
        List<Long> guidList = viewForm.getGuidList();

        Integer state = stagesManageMapper.deleteStages(guidList, viewForm.getCurrId(), viewForm.getCurrName());
        if (state <= 0) {
            throw new RuntimeException("删除分期优惠失败！");
        } else {
            Integer state1 = stagesManageMapper.deleteStagesItem(guidList, viewForm.getCurrId(), viewForm.getCurrName());
            if (state1 > 0) {
                return RestResponse.success("删除分期优惠成功！");
            } else {
                throw new RuntimeException("删除分期优惠失败！");
            }
        }
    }

    /**
     * 修改分期活动
     * 已完成
     *
     * @param viewForm
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class, timeout = 30)
    public RestResponse updateStages(UpStagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        DSStages dSstages = new DSStages();
        //入参 guid
        dSstages.setGuid(viewForm.getGuid());
        //入参 要修改的东西
        dSstages.setIssues(viewForm.getIssues());
        dSstages.setName(viewForm.getName());
        //修改人信息
        dSstages.setModUser(viewForm.getCurrName());
        dSstages.setModUserId(viewForm.getCurrId());
        dSstages.setModTime(LocalDateTime.now());
        //修改主表
        Integer state = stagesManageMapper.updateStages(dSstages);
        if (state <= 0) {
            throw new RuntimeException("修改分期信息失败！");
        } else {

            Integer state1 = stagesManageMapper.moveStagesItem(dSstages);
            if (state1 <= 0) {
                throw new RuntimeException("修改分期信息失败！");
            } else {
                List<DSStagesItem> itemList = new ArrayList<>(16);
                List<UpStagesItemViewForm> itemViewFormList = viewForm.getItemList();
                //遍历到list中
                itemViewFormList.forEach(e -> {
                    DSStagesItem dsStagesItem = new DSStagesItem();
                    dsStagesItem.setStageGuid(dSstages.getGuid());
                    //还款期次
                    dsStagesItem.setRepayIssue(e.getRepayIssue());
                    //还款金额
                    dsStagesItem.setRepayAmount(e.getRepayAmount());
                    //添加人
                    dsStagesItem.setAddUserId(viewForm.getCurrId());
                    dsStagesItem.setAddUser(viewForm.getCurrName());
                    dsStagesItem.setAddTime(LocalDateTime.now());
                    //修改人
                    dsStagesItem.setModUserId(viewForm.getCurrId());
                    dsStagesItem.setModUser(viewForm.getCurrName());
                    dsStagesItem.setModTime(LocalDateTime.now());
                    dsStagesItem.setDeleteFlag(false);
                    itemList.add(dsStagesItem);
                });
                //添加子表信息
                Integer num = stagesManageMapper.addStagesItem(itemList);
                if (num > 0) {
                    return RestResponse.success("修改分期信息成功！");
                } else {
                    throw new RuntimeException("修改分期信息失败！");
                }
            }
        }
    }


    /**
     * 启用优惠
     * 已完成
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse updateStagesEnable(StagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        List<Long> list = viewForm.getGuidList();
        Integer state = stagesManageMapper.updateStagesEnable(list, viewForm.getCurrId(), viewForm.getCurrName());
        if (state > 0) {
            return RestResponse.success("启用分期优惠成功！");
        } else {
            throw new RuntimeException("启用分期优惠失败！");
        }
    }

    /**
     * 停用优惠
     * 已完成
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse updateStagesUnEnable(StagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        List<Long> list = viewForm.getGuidList();
        Integer state = stagesManageMapper.updateStagesUnEnable(list, viewForm.getCurrId(), viewForm.getCurrName());
        if (state > 0) {
            return RestResponse.success("停用分期优惠成功！");
        } else {
            throw new RuntimeException("停用分期优惠失败！");
        }
    }

    /**
     * 分期活动一览
     * 已完成
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse getStagesList(GetStagesViewForm viewForm) {
        //权限验证
        roleCheck(viewForm.getCurrRole());

        DSStages dSstages = new DSStages();
        //入参 可有可无
        dSstages.setStatus(viewForm.getStatus());

        dSstages.setCostId(viewForm.getCostId());
        //获取信息存入list
        List<DSStages> reDsStages = stagesManageMapper.getStagesList(dSstages);
        List<GetStagesInfoVO> getStagesInfoVOList = new ArrayList<>(16);
        //缓存信息到VO中
        reDsStages.forEach(e -> {
            GetStagesInfoVO getStagesInfoVO = new GetStagesInfoVO();
            getStagesInfoVO.setGuid(e.getGuid());
            getStagesInfoVO.setName(e.getName());
            getStagesInfoVO.setIssues(e.getIssues());
            getStagesInfoVO.setStatus(e.getStatus());
            //将缓存的信息放入list中
            getStagesInfoVOList.add(getStagesInfoVO);
        });
        //返回VOList中的说有数据
        return RestResponse.success(getStagesInfoVOList);
    }

    /**
     * 查看分期活动详情
     * 已完成
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse getStagesInfo(GetStagesInfoViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        DSStages dSstages = new DSStages();
        //入参
        dSstages.setGuid(viewForm.getGuid());
        dSstages.setName(viewForm.getName());
        DSStages stages = stagesManageMapper.getStagesInfo(dSstages);
        GetStagesInfoVO getStagesInfoVO = new GetStagesInfoVO();
        //缓存信息
        getStagesInfoVO.setGuid(stages.getGuid());
        getStagesInfoVO.setName(stages.getName());
        getStagesInfoVO.setIssues(stages.getIssues());
        getStagesInfoVO.setStatus(stages.getStatus());
        //返回信息
        return RestResponse.success(getStagesInfoVO);
    }

    /**
     * 权限判断
     */
    public void roleCheck(Integer role) {
        if (!Constant.MANAGE_ROLE.equals(role)) {
            if (!Constant.MARKETING_ROLE.equals(role)) {
                log.debug("权限不足!");
                throw new RuntimeException("权限不足！");
            }
        }
    }

}
