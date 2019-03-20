package com.cn.school.service.web.impl;

import com.cn.school.constant.Constant;
import com.cn.school.dto.forms.EvaluateManage.GetEvaluateViewForm;
import com.cn.school.dto.forms.stagesmanage.*;
import com.cn.school.dto.info.vo.GetCoachInfoVO;
import com.cn.school.dto.info.vo.GetStagesInfoVO;
import com.cn.school.dto.info.vo.GetStuInfoVO;
import com.cn.school.dto.info.vo.GetStudentInfoVO;
import com.cn.school.entity.DSStagesItem;
import com.cn.school.entity.DSStudents;
import com.cn.school.entity.DSUser;
import com.cn.school.entity.DSstages;
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
     *已完成
     * @param viewForm
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public RestResponse addStages(AddStagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        //主表
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
        //插入信息到分期优惠表主表
         Long st =  stagesManageMapper.addStages(dSstages);
        if (st < 0) {
            return RestResponse.success("添加分期信息失败！");
        }
        List<DSStagesItem> itemList = new ArrayList<>(16);
        List<AddStagesItemViewForm> itemViewFormList = viewForm.getItemList();
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
        Integer num = stagesManageMapper.addStagesItem(itemList);
        if (num > 0) {
            return RestResponse.success("添加分期信息成功！");
        } else {
            return RestResponse.error("添加分期信息失败！");
        }
    }

    /**
     * 删除分期活动
     *已完成
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse deleteStages(DeleteStagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        DSstages dSstages = new DSstages();
        //r入参 分期活动的guid与name
        dSstages.setGuid(viewForm.getGuid());
        dSstages.setName(viewForm.getName());
        //修改人姓名
        dSstages.setModUser(viewForm.getCurrName());
        //修改人id
        dSstages.setModUserId(viewForm.getCurrId());
        //删除时间
        dSstages.setModTime(LocalDateTime.now());
        Integer state = stagesManageMapper.deleteStages(dSstages);
        if (state < 0) {
            return RestResponse.error("删除分期优惠失败！");
                    }
        else {
            Integer state1 = stagesManageMapper.deleteStagesItem(dSstages);
            if (state1 > 0) {
                return RestResponse.success("删除分期优惠成功！");
            } else {
                return RestResponse.error("删除分期优惠失败！");
            }
        }
    }

    /**
     * 修改分期活动
     *已完成
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse updateStages(UpStagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        DSstages dSstages = new DSstages();
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
        if (state<0){
            return RestResponse.error("修改分期信息失败！");
        }
       else {
           Integer state1 = stagesManageMapper.moveStagesItem(dSstages);
           if (state1 < 0){
               return RestResponse.error("修改分期信息失败！");
           }
           else {
               List<DSStagesItem> itemList = new ArrayList<>(16);
               List<AddStagesItemViewForm> itemViewFormList = viewForm.getItemList();
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
               Integer num = stagesManageMapper.addStagesItem(itemList);
               if (num > 0) {
                   return RestResponse.success("添加分期信息成功！");
               } else {
                   return RestResponse.error("添加分期信息失败！");
               }
               }
           }
    }


    /**
     * 启用优惠
     * 已完成
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse updateStagesEnable(StagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        DSstages dSstages = new DSstages();
        dSstages.setGuid(viewForm.getGuid());
        //修改人姓名
        dSstages.setModUser(viewForm.getCurrName());
        //修改人id
        dSstages.setModUserId(viewForm.getCurrId());
        //启用时间
        dSstages.setModTime(LocalDateTime.now());
        Integer state = stagesManageMapper.updateStagesEnable(dSstages);
        if (state > 0) {
            return RestResponse.success("启用分期优惠成功！");
        } else {
            return RestResponse.error("启用分期优惠失败！");
        }
    }

    /**
     * 停用优惠
     * 已完成
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse updateStagesUnEnable(StagesViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        DSstages dSstages = new DSstages();
        dSstages.setGuid(viewForm.getGuid());
        //修改人姓名
        dSstages.setModUser(viewForm.getCurrName());
        //修改人id
        dSstages.setModUserId(viewForm.getCurrId());
        //启用时间
        dSstages.setModTime(LocalDateTime.now());
        Integer state = stagesManageMapper.updateStagesUnEnable(dSstages);
        if (state > 0) {
            return RestResponse.success("停用分期优惠成功！");
        } else {
            return RestResponse.error("停用分期优惠失败！");
        }
    }

    /**
     * 分期活动一览
     *已完成
     * @param viewForm
     * @return
     */
    @Override
    public List getStagesList(GetStagesViewForm viewForm) {
        //权限验证
        roleCheck(viewForm.getCurrRole());

        DSstages dSstages = new DSstages();
        //入参 可有可无
        dSstages.setStatus(viewForm.getStatus());
        //获取信息存入list
        List<DSstages> reDsStages = stagesManageMapper.getStagesList(dSstages);
        List<GetStagesInfoVO> getStagesInfoVOList = new ArrayList<>(16);
        //缓存信息到VO中
        reDsStages.forEach(e -> {
            GetStagesInfoVO getStagesInfoVO = new GetStagesInfoVO();
            getStagesInfoVO.setGuid(e.getGuid());
            getStagesInfoVO.setName(e.getName());
            getStagesInfoVO.setIssues(e.getIssues());
            getStagesInfoVO.setStatus(e.getStatus());
            getStagesInfoVO.setAddUser(e.getAddUser());
            getStagesInfoVO.setAddUserId(e.getAddUserId());
            getStagesInfoVO.setAddTime(e.getAddTime());
            getStagesInfoVO.setModUser(e.getModUser());
            getStagesInfoVO.setModUserId(e.getModUserId());
            getStagesInfoVO.setModTime(e.getModTime());
            //将缓存的信息放入list中
            getStagesInfoVOList.add(getStagesInfoVO);
        });
        //返回VOList中的说有数据
        return getStagesInfoVOList;
    }

    /**
     * 查看分期活动详情
     *已完成
     * @param getStagesViewForm
     * @return
     */
    @Override
    public RestResponse getStagesInfo(GetStagesViewForm getStagesViewForm) {
        //权限判断
        roleCheck(getStagesViewForm.getCurrRole());

        DSstages dSstages = new DSstages();
        //入参
        dSstages.setGuid(getStagesViewForm.getGuid());
        dSstages.setName(getStagesViewForm.getName());
        DSstages stages = stagesManageMapper.getStagesInfo(dSstages);
        GetStagesInfoVO getStagesInfoVO = new GetStagesInfoVO();
        //缓存信息
        getStagesInfoVO.setGuid(stages.getGuid());
        getStagesInfoVO.setName(stages.getName());
        getStagesInfoVO.setIssues(stages.getIssues());
        getStagesInfoVO.setStatus(stages.getStatus());
        getStagesInfoVO.setAddUser(stages.getAddUser());
        getStagesInfoVO.setAddUserId(stages.getAddUserId());
        getStagesInfoVO.setAddTime(stages.getAddTime());
        getStagesInfoVO.setModUser(stages.getModUser());
        getStagesInfoVO.setModUserId(stages.getModUserId());
        getStagesInfoVO.setModTime(stages.getModTime());
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
