package com.cn.school.service.web.impl;

import com.cn.school.constant.Constant;
import com.cn.school.dto.forms.EvaluateManage.AddEvaluateViewForm;
import com.cn.school.dto.forms.EvaluateManage.DeleteEvaluateViewForm;
import com.cn.school.dto.forms.EvaluateManage.GetEvaluateViewForm;
import com.cn.school.dto.info.vo.GetEvaluateInfoVO;
import com.cn.school.dto.info.vo.StuGetEvaluateInfoVO;
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
     * 管理员删除评价与投诉,假删除（更新状态）
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse deleteEvaluate(DeleteEvaluateViewForm viewForm) {
        roleCheck(viewForm.getCurrRole());
        //删除条件-查找主键guid
        List<Long> guidList = viewForm.getGuidList();
        //执行删除操作并进行判断
        Integer state = evaluateManageMapper.deleteEvaluate(guidList,viewForm.getCurrId(),viewForm.getCurrName());
        if (state > 0) {
            return RestResponse.success("删除评价信息成功！");
        } else {
            return RestResponse.error("删除评价信息失败！");
        }
    }

    /**
     * 管理员查看评价与投诉
     * @param evaluateViewForm
     * @return
     */
    @Override
    public RestResponse getEvaluates(GetEvaluateViewForm evaluateViewForm) {
        //权限判断
        roleCheck(evaluateViewForm.getCurrRole());
        DSEvaluate dsEvaluate = new DSEvaluate();
        //条件查询-根据guid（若输入guid则根据guid来查询，什么都不输入则查看所有）
        dsEvaluate.setGuid(evaluateViewForm.getGuid());
        //根据添加人姓名进行查询
        dsEvaluate.setAddUser(evaluateViewForm.getAddUser());
        //根据添加时间查询
        dsEvaluate.setAddTime(evaluateViewForm.getAddTime());
        //执行查询操作并将搜查到的信息放入List中
        List<DSEvaluate> reDSEva = evaluateManageMapper.getEvaluates(dsEvaluate);
        List<GetEvaluateInfoVO> getEvaluateInfoVOList = new ArrayList<>(16);
        //遍历List缓存到getEvaluateInfoVOList中
        reDSEva.forEach(e -> {
            GetEvaluateInfoVO getEvaluateInfoVO = new GetEvaluateInfoVO();
            getEvaluateInfoVO.setGuid(e.getGuid());
            getEvaluateInfoVO.setAddUser(e.getAddUser());
            getEvaluateInfoVO.setAddTime(e.getAddTime());
            getEvaluateInfoVO.setScore(e.getScore());
            getEvaluateInfoVO.setComments(e.getComments());
            getEvaluateInfoVOList.add(getEvaluateInfoVO);
        });
        return RestResponse.success(getEvaluateInfoVOList) ;
    }
    /**
     * 管理员查看评价与投诉详细
     * @param evaluateViewForm
     * @return
     */
    @Override
    public RestResponse getEvaluate(GetEvaluateViewForm evaluateViewForm) {
        //权限判断
        roleCheck(evaluateViewForm.getCurrRole());
        DSEvaluate dsEvaluate = new DSEvaluate();
        dsEvaluate.setAddUser(evaluateViewForm.getAddUser());
        //执行查询操作并将搜查到的信息放入List中
        List<DSEvaluate> reDSEva = evaluateManageMapper.getEvaluate(dsEvaluate);
        List<GetEvaluateInfoVO> getEvaluateInfoVOList = new ArrayList<>(16);
        //遍历List缓存到getEvaluateInfoVOList中
        reDSEva.forEach(e -> {
            GetEvaluateInfoVO getEvaluateInfoVO = new GetEvaluateInfoVO();
            getEvaluateInfoVO.setGuid(e.getGuid());
            getEvaluateInfoVO.setAddUser(e.getAddUser());
            getEvaluateInfoVO.setAddTime(e.getAddTime());
            getEvaluateInfoVO.setScore(e.getScore());
            getEvaluateInfoVO.setComments(e.getComments());
            getEvaluateInfoVOList.add(getEvaluateInfoVO);
        });
        return RestResponse.success(getEvaluateInfoVOList) ;
    }
    /**
     * 学员添加评价与投诉
     * @param addEvaluateViewForm
     * @return
     */
    @Override
    public RestResponse addEvaluate(AddEvaluateViewForm addEvaluateViewForm) {
        DSEvaluate dsEvaluate = new DSEvaluate();
        //获取评与投诉内容
        dsEvaluate.setComments(addEvaluateViewForm.getComments());
        //学员对评价或投诉的对象打分
        dsEvaluate.setScore(addEvaluateViewForm.getScore());
        //评价与投诉人信息
        dsEvaluate.setAddUser(addEvaluateViewForm.getCurrName());
        dsEvaluate.setAddUserId(addEvaluateViewForm.getCurrId());
        //评价与投诉时间
        dsEvaluate.setAddTime(LocalDateTime.now());
        dsEvaluate.setModUserId(addEvaluateViewForm.getCurrId());
        dsEvaluate.setModUser(addEvaluateViewForm.getCurrName());
        dsEvaluate.setModTime(LocalDateTime.now());
        //执行插入操作
        Integer state = evaluateManageMapper.addEvaluate(dsEvaluate);
        if (state > 0) {
            return RestResponse.success("成功！");
        } else {
            throw new RuntimeException("失败！");
        }
    }
    /**
     * 学员一览评价与投诉
     * @return
     */
    @Override
    public RestResponse stuGetEvaluates() {
        //获取评价与投诉信息并缓存
        List<DSEvaluate> reDSEva = evaluateManageMapper.stuGetEvaluates();
        List<StuGetEvaluateInfoVO> getEvaluateInfoVOList = new ArrayList<>(16);
        //遍历缓存到getEvaluateInfoVOList中
        reDSEva.forEach(e -> {
            StuGetEvaluateInfoVO getEvaluateInfoVO = new StuGetEvaluateInfoVO();
            getEvaluateInfoVO.setAddUser(e.getAddUser());
            getEvaluateInfoVO.setAddTime(e.getAddTime());
            getEvaluateInfoVO.setScore(e.getScore());
            getEvaluateInfoVO.setComments(e.getComments());
            getEvaluateInfoVOList.add(getEvaluateInfoVO);
        });
        return RestResponse.success(getEvaluateInfoVOList) ;
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
