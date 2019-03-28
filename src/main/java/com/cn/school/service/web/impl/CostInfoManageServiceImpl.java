package com.cn.school.service.web.impl;

import com.cn.school.constant.Constant;
import com.cn.school.dto.forms.costinfomanage.AddCostManageViewForm;
import com.cn.school.dto.forms.costinfomanage.DelCostManageViewForm;
import com.cn.school.dto.forms.costinfomanage.GetCostManageListViewForm;
import com.cn.school.dto.forms.costinfomanage.UpCostManageViewForm;
import com.cn.school.dto.info.vo.GetCostInfoManageVO;
import com.cn.school.entity.DSCostInfo;
import com.cn.school.mapper.web.CostInfoManageMapper;
import com.cn.school.service.web.CostInfoManageService;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:HuMin Date:2019/2/26
 * Time:10:42
 */
@Slf4j
@Service
public class CostInfoManageServiceImpl implements CostInfoManageService {

    @Autowired
    private CostInfoManageMapper costInfoManageMapper;

    /**
     * 获取报名信息list
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse getCostInfoList(GetCostManageListViewForm viewForm) {
        roleCheck(viewForm.getCurrRole());
        List<DSCostInfo> list = costInfoManageMapper.getCostInfoList();
        List<GetCostInfoManageVO> infoVOList = new ArrayList<>(16);
        if (!list.isEmpty()) {
            list.forEach(e -> {
                GetCostInfoManageVO Info = new GetCostInfoManageVO();
                Info.setGuid(e.getGuid());
                //驾照登记
                Info.setDriverLevel(e.getDriverLevel());
                //金额
                Info.setDriverMoney(e.getDriverMoney());
                Info.setAddUser(e.getAddUser());
                Info.setAddTime(e.getAddTime());
                Info.setModTime(e.getModTime());
                Info.setModUser(e.getModUser());
                infoVOList.add(Info);
            });
        }
        return RestResponse.success(infoVOList);
    }

    /**
     * 添加报名费用信息
     *
     * @param viewForm
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class, timeout = 30)
    public RestResponse addCostInfo(AddCostManageViewForm viewForm) {
        roleCheck(viewForm.getCurrRole());
        DSCostInfo dsCostInfo = new DSCostInfo();
        // 驾照类型（等级）
        dsCostInfo.setDriverLevel(viewForm.getDriverLevel());
        //金额
        dsCostInfo.setDriverMoney(viewForm.getDriverMoney());

        //添加人
        dsCostInfo.setAddTime(LocalDateTime.now());
        dsCostInfo.setAddUser(viewForm.getCurrName());
        dsCostInfo.setAddUserId(viewForm.getCurrId());
        dsCostInfo.setModTime(LocalDateTime.now());
        dsCostInfo.setModUser(viewForm.getCurrName());
        dsCostInfo.setModUserId(viewForm.getCurrId());

        //未删除状态
        dsCostInfo.setDeleteFlag(false);
        Integer num = costInfoManageMapper.addCostInfo(dsCostInfo);
        if (num < 0) {
            throw new RuntimeException("添加失败");
        }
        return RestResponse.success("添加成功");
    }

    /**
     * 更新报名费用信息
     *
     * @param viewForm
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class, timeout = 30)
    public RestResponse updateCostInfo(UpCostManageViewForm viewForm) {
        roleCheck(viewForm.getCurrRole());
        DSCostInfo dsCostInfo = new DSCostInfo();

        dsCostInfo.setGuid(viewForm.getGuid());

        // 驾照类型（等级）
        dsCostInfo.setDriverLevel(viewForm.getDriverLevel());
        //金额
        dsCostInfo.setDriverMoney(viewForm.getDriverMoney());
        //修改人
        dsCostInfo.setModTime(LocalDateTime.now());
        dsCostInfo.setModUser(viewForm.getCurrName());
        dsCostInfo.setModUserId(viewForm.getCurrId());

        Integer num = costInfoManageMapper.updateCostInfo(dsCostInfo);
        if (num <= 0) {
            throw new RuntimeException("修改失败");
        }
        return RestResponse.success("修改成功");
    }

    /**
     * 删除报名费用信息
     *
     * @param viewForm
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class, timeout = 30)
    public RestResponse deleteCostInfo(DelCostManageViewForm viewForm) {
        roleCheck(viewForm.getCurrRole());
        List<Long> guidList = viewForm.getGuidList();

        Integer num = costInfoManageMapper.deleteCostInfo(guidList, viewForm.getCurrId(), viewForm.getCurrName());
        if (num <= 0) {
            throw new RuntimeException("删除失败");
        }
        return RestResponse.success("删除成功");
    }

    /**
     * 权限判断
     * 只用管理员与市场部成员看到
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
