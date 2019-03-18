package com.cn.school.service.web.impl;

import com.cn.school.dto.forms.EvaluateManage.DeleteEvaluateViewForm;
import com.cn.school.dto.forms.EvaluateManage.GetEvaluateViewForm;
import com.cn.school.dto.info.vo.GetEvaluateInfoVO;
import com.cn.school.entity.DSEvaluate;
import com.cn.school.mapper.web.EvaluateManageMapper;
import com.cn.school.service.web.EvaluateManageService;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EvaluateManageServiceImpl implements EvaluateManageService {

    @Autowired
    private EvaluateManageMapper evaluateManageMapper;

    /**
     * 评价删除,假删除（更新状态）
     *
     * @param deleteEvaluateViewForm
     * @return
     */
    @Override
    public RestResponse deleteEvaluate(DeleteEvaluateViewForm deleteEvaluateViewForm) {
        if (deleteEvaluateViewForm.getCurrRole() != 3) {
            log.debug("权限不足!");
            return RestResponse.error("权限不足！");
        }
        DSEvaluate dsEvaluate = new DSEvaluate();
        dsEvaluate.setAddUser(deleteEvaluateViewForm.getAddUser());
        dsEvaluate.setAddUserId(deleteEvaluateViewForm.getAddUserId());
        dsEvaluate.setModUser(deleteEvaluateViewForm.getCurrName());
        dsEvaluate.setModUserId(deleteEvaluateViewForm.getCurrId());
        dsEvaluate.setModTime(LocalDateTime.now());
        Integer state = evaluateManageMapper.deleteEvaluate(dsEvaluate);
        if (state > 0) {
            return RestResponse.success("删除评价信息成功！");
        } else {
            return RestResponse.error("删除评价信息失败！");
        }
    }

    /**
     * 管理员查看学员信息
     *
     * @param evaluateViewForm
     * @return
     */
    @Override
    public List getEvaluates(GetEvaluateViewForm evaluateViewForm) {
        //TODO 添加权限模块 管理员查看所有教练员基本信息
        DSEvaluate dsEvaluate = new DSEvaluate();
        dsEvaluate.setAddUser(evaluateViewForm.getAddUser());
        dsEvaluate.setAddTime(evaluateViewForm.getAddTime());
        dsEvaluate.setComments(evaluateViewForm.getComments());
        dsEvaluate.setScore(evaluateViewForm.getScore());
        List<DSEvaluate> reDSEva = evaluateManageMapper.getEvaluates(dsEvaluate);
        List<GetEvaluateInfoVO> getEvaluateInfoVOList = new ArrayList<>(16);
        reDSEva.forEach(e -> {
            GetEvaluateInfoVO getEvaluateInfoVO = new GetEvaluateInfoVO();
            getEvaluateInfoVO.setAddUser(e.getAddUser());
            getEvaluateInfoVO.setAddTime(e.getAddTime());
            getEvaluateInfoVO.setScore(e.getScore());
            getEvaluateInfoVO.setData(e.getComments());
            getEvaluateInfoVOList.add(getEvaluateInfoVO);
        });
        return getEvaluateInfoVOList;
    }

}
