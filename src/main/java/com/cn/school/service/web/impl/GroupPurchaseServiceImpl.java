package com.cn.school.service.web.impl;

import com.cn.school.constant.Constant;
import com.cn.school.dto.forms.usermanage.GroupPurchaseViewForm;
import com.cn.school.dto.info.vo.getGroupPurchaseInfoVO;
import com.cn.school.entity.DSGrpPurchase;
import com.cn.school.mapper.web.GroupPurchaseMapper;
import com.cn.school.service.web.GroupPurchaseService;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GroupPurchaseServiceImpl implements GroupPurchaseService {

    @Autowired
    private GroupPurchaseMapper groupPurchaseMapper;

    /**
     * 管理员生成团购活动内容，内容信息只能由管理员添加
     *
     * @param groupPurchaseViewForm
     * @return
     */
    @Override
    public RestResponse addGroupPurchase(GroupPurchaseViewForm groupPurchaseViewForm) {
        //权限判断
        if (!Constant.MANAGE_ROLE.equals(groupPurchaseViewForm.getCurrRole())) {
            return RestResponse.error("权限不足！");
        }
        DSGrpPurchase dsGrpPurchase = new DSGrpPurchase();
        //入参
        dsGrpPurchase.setGpNname(groupPurchaseViewForm.getGpNname());
        dsGrpPurchase.setPeopleNum(groupPurchaseViewForm.getPeopleNum());
        dsGrpPurchase.setDriverLevel(groupPurchaseViewForm.getDriverLevel());
        dsGrpPurchase.setAggregateAmount(groupPurchaseViewForm.getAggregateAmount());
        dsGrpPurchase.setIssue(groupPurchaseViewForm.getIssue());
        dsGrpPurchase.setCoupon(groupPurchaseViewForm.getCoupon());
        //添加教练员时，获取登录人ID
        dsGrpPurchase.setAddUserId(groupPurchaseViewForm.getCurrId());
        //添加教练员时，获取登录人名称
        dsGrpPurchase.setAddUser(groupPurchaseViewForm.getCurrName());
        //添加教练员时，获取当前时间
        dsGrpPurchase.setAddTime(LocalDateTime.now());
        //修改教练员时，获取登录人ID
        dsGrpPurchase.setModUserId(groupPurchaseViewForm.getCurrId());
        //修改教练员时，获取登录人名称
        dsGrpPurchase.setModUser(groupPurchaseViewForm.getCurrName());
        //修改教练员时，获取当前时间
        dsGrpPurchase.setModTime(LocalDateTime.now());
        Integer state = groupPurchaseMapper.addGroupPurchase(dsGrpPurchase);
        if (state > 0) {
            return RestResponse.success("添加团购活动内容成功！");
        } else {
            return RestResponse.error("添加团购活动内容失败！");
        }
    }

    /**
     * 全部团购活动一览
     *
     * @param groupPurchaseViewForm
     * @return
     */
    @Override
    public List getGroupPurchaseList(GroupPurchaseViewForm groupPurchaseViewForm) {
        //TODO 添加权限模块 管理员查看全部团购活动
        DSGrpPurchase dsGrpPurchase = new DSGrpPurchase();
        //入参
        dsGrpPurchase.setGpNname(groupPurchaseViewForm.getGpNname());
        dsGrpPurchase.setPeopleNum(groupPurchaseViewForm.getPeopleNum());
        dsGrpPurchase.setDriverLevel(groupPurchaseViewForm.getDriverLevel());
        dsGrpPurchase.setAggregateAmount(groupPurchaseViewForm.getAggregateAmount());
        dsGrpPurchase.setIssue(groupPurchaseViewForm.getIssue());
        dsGrpPurchase.setCoupon(groupPurchaseViewForm.getCoupon());
        List<DSGrpPurchase> reDSGrpPurchase = groupPurchaseMapper.getGroupPurchaseList(dsGrpPurchase);
        List<getGroupPurchaseInfoVO> getGroupPurchaseVOList = new ArrayList<>(16);
        //出参
        reDSGrpPurchase.forEach(e -> {
            getGroupPurchaseInfoVO getGroupPurchaseVO = new getGroupPurchaseInfoVO();
            getGroupPurchaseVO.setGpNname(e.getGpNname());
            getGroupPurchaseVO.setPeopleNum(e.getPeopleNum());
            getGroupPurchaseVO.setDriverLevel(e.getDriverLevel());
            getGroupPurchaseVO.setAggregateAmount(e.getAggregateAmount());
            getGroupPurchaseVO.setIssue(e.getIssue());
            getGroupPurchaseVO.setCoupon(e.getCoupon());
            getGroupPurchaseVOList.add(getGroupPurchaseVO);
        });
        return getGroupPurchaseVOList;
    }
}
