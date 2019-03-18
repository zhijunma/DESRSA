package com.cn.school.service.web.impl;

import com.cn.school.dto.forms.usermanage.*;
import com.cn.school.dto.info.vo.GetCoachInfoVO;
import com.cn.school.dto.info.vo.GetStuInfoVO;
import com.cn.school.dto.info.vo.GetUserInfoVO;
import com.cn.school.entity.DSUser;
import com.cn.school.mapper.web.UsersMapper;
import com.cn.school.mapstruct.UserMapStruct;
import com.cn.school.service.web.UsersService;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 人员管理模块
 */
@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserMapStruct userMapStruct;
    /**
     * 学员角色
     */
    private final Integer stuRole = 1;
    /**
     * 教练角色
     */
    private final Integer coachRole = 2;
    /**
     * 用户查看个人信息
     *
     * @param userViewForm
     * @return
     */
    @Override
    public RestResponse getUser(GetUserViewForm userViewForm) {
        DSUser dsUser = usersMapper.getUser(userViewForm.getCurrId());
        GetUserInfoVO getUserInfoVO = new GetUserInfoVO();
        getUserInfoVO.setIdCard(dsUser.getIdCard());
        getUserInfoVO.setMobilePhone(dsUser.getMobilePhone());
        getUserInfoVO.setRole(dsUser.getRole());
        getUserInfoVO.setStatus(dsUser.getStatus());
        getUserInfoVO.setUserName(dsUser.getUserName());
        return RestResponse.success(getUserInfoVO);
    }

    /**
     * 用户修改个人信息
     *
     * @param userViewForm
     * @return
     */
    @Override
    public RestResponse updateUsers(UpdateUserViewForm userViewForm) {
        Integer state = usersMapper.updateUsers(userViewForm.getCurrId(), userViewForm.getPassword(), userViewForm.getMobilePhone(), userViewForm.getIdCard());
        if (state > 0) {
            return RestResponse.success("修改个人信息成功！");
        } else {
            return RestResponse.error("修改个人信息失败！");
        }

    }

    /**
     * 教练员添加，教练员信息只能由管理员添加
     *
     * @param insertCoachViewForm
     * @return
     */
    @Override
    public RestResponse addCoach(InsertCoachViewForm insertCoachViewForm) {
        //权限判断
        if (insertCoachViewForm.getCurrRole() != 3) {
            return RestResponse.error("权限不足！");
        }
        DSUser dsUser = new DSUser();
        //入参
        dsUser.setUserName(insertCoachViewForm.getUserName());
        //盐
        String salt = UUID.randomUUID().toString();
        dsUser.setSalt(salt);
        //用盐对密码加密getPassword()
        dsUser.setPassword(DigestUtils.sha256Hex(DigestUtils.sha256Hex(insertCoachViewForm.getPassword()) + salt));
        dsUser.setMobilePhone(insertCoachViewForm.getMobilePhone());
        dsUser.setIdCard(insertCoachViewForm.getIdCard());
        //添加教练员角色为“2”（“2”为教练员角色）
        dsUser.setRole(coachRole);
        //状态 3：空闲（教练员） 4：忙碌（教练员）
        dsUser.setStatus(0);
        //添加教练员时，获取登录人ID
        dsUser.setAddUserId(insertCoachViewForm.getCurrId());
        //添加教练员时，获取登录人名称
        dsUser.setAddUser(insertCoachViewForm.getCurrName());
        //添加教练员时，获取当前时间
        dsUser.setAddTime(LocalDateTime.now());
        //修改教练员时，获取登录人ID
        dsUser.setModUserId(insertCoachViewForm.getCurrId());
        //修改教练员时，获取登录人名称
        dsUser.setModUser(insertCoachViewForm.getCurrName());
        //修改教练员时，获取当前时间
        dsUser.setModTime(LocalDateTime.now());
        dsUser.setDeleteFlag(false);
        Integer state = usersMapper.addCoach(dsUser);
        if (state > 0) {
            return RestResponse.success("添加教练员信息成功！");
        } else {
            return RestResponse.error("添加教练员信息失败！");
        }
    }

    /**
     * 教练员信息一览 管理员查看所有教练员基本信息
     *
     * @param GetCoachsViewForm
     * @return
     */
    @Override
    public List getCoachs(GetCoachsViewForm GetCoachsViewForm) {
        //TODO 添加权限模块 管理员查看所有教练员基本信息
        DSUser dsUser = new DSUser();
        dsUser.setUserName(GetCoachsViewForm.getUserName());
        dsUser.setMobilePhone(GetCoachsViewForm.getMobilePhone());
        dsUser.setIdCard(GetCoachsViewForm.getIdCard());
        dsUser.setStatus(GetCoachsViewForm.getStatus());
        List<DSUser> reDsUser = usersMapper.getCoachs(dsUser);
        List<GetCoachInfoVO> getCoachInfoVOList = new ArrayList<>(16);
        reDsUser.forEach(e -> {
            GetCoachInfoVO getCoachInfoVO = new GetCoachInfoVO();
            getCoachInfoVO.setGuid(e.getGuid());
            getCoachInfoVO.setUserName(e.getUserName());
            getCoachInfoVO.setRole(e.getRole());
            getCoachInfoVO.setMobilePhone(e.getMobilePhone());
            getCoachInfoVO.setIdCard(e.getIdCard());
            getCoachInfoVO.setStatus(e.getStatus());
            getCoachInfoVOList.add(getCoachInfoVO);
        });
        return getCoachInfoVOList;
    }

    /**
     * 教练员删除,假删除（更新状态）
     *
     * @param deleteCoachViewForm
     * @return
     */
    @Override
    public RestResponse deleteCoach(DeleteCoachViewForm deleteCoachViewForm) {
        if (deleteCoachViewForm.getCurrRole() != 3) {
            log.debug("权限不足!");
            return RestResponse.error("权限不足！");
        }
        DSUser dsUser = new DSUser();
        dsUser.setIdCard(deleteCoachViewForm.getIdCard());
        dsUser.setModUserId(deleteCoachViewForm.getCurrId());
        dsUser.setModUser(deleteCoachViewForm.getCurrName());
        dsUser.setModTime(LocalDateTime.now());
        Integer state = usersMapper.deleteCoach(dsUser);
        if (state > 0) {
            return RestResponse.success("删除教练员成功！");
        } else {
            return RestResponse.error("删除教练员失败！");
        }
    }

    /**
     * 教练员详情查看（根据身份证号查询教练员）
     *
     * @param getCoachViewForm
     * @return
     */
    @Override
    public RestResponse getCoach(GetCoachViewForm getCoachViewForm) {
        if (getCoachViewForm.getCurrRole() != 3) {
            return RestResponse.error("权限不足！");
        }
        //入参
        DSUser dsUser = new DSUser();
        dsUser.setGuid(getCoachViewForm.getGuid());
        dsUser.setUserName(getCoachViewForm.getUserName());
        dsUser.setMobilePhone(getCoachViewForm.getMobilePhone());
        dsUser.setIdCard(getCoachViewForm.getIdCard());
        DSUser dsUser1 = usersMapper.getCoach(dsUser);
        //出参
        GetCoachInfoVO getCoachInfoVO = new GetCoachInfoVO();
        getCoachInfoVO.setGuid(dsUser1.getGuid());
        getCoachInfoVO.setUserName(dsUser1.getUserName());
        getCoachInfoVO.setRole(dsUser1.getRole());
        getCoachInfoVO.setMobilePhone(dsUser1.getMobilePhone());
        getCoachInfoVO.setIdCard(dsUser1.getIdCard());
        getCoachInfoVO.setStatus(dsUser1.getStatus());
        //TODO 判断是否查询到数据 防止程序出现错误
        if (getCoachInfoVO.getGuid() != null) {
            return RestResponse.success(getCoachInfoVO);
        }else {
            return RestResponse.success("没有符合条件的教练信息");
        }
//        return RestResponse.success(getCoachInfoVO);
    }

    /**
     * 教练员信息修改（只能由管理员修改）
     *
     * @param updateCoachViewForm
     * @return
     */
    @Override
    public RestResponse updateCoach(UpdateCoachViewForm updateCoachViewForm) {
        //权限判断
        if (updateCoachViewForm.getCurrRole() != 3) {
            log.debug("权限不足!");
            return RestResponse.error("权限不足！");
        }
        //入参
        DSUser dsUser = new DSUser();
        dsUser.setGuid(updateCoachViewForm.getGuid());
        dsUser.setUserName(updateCoachViewForm.getUserName());
        dsUser.setPassword(updateCoachViewForm.getPassword());
        dsUser.setMobilePhone(updateCoachViewForm.getMobilePhone());
        dsUser.setIdCard(updateCoachViewForm.getIdCard());
        dsUser.setStatus(updateCoachViewForm.getStatus());
        dsUser.setModUserId(updateCoachViewForm.getCurrId());
        dsUser.setModUser(updateCoachViewForm.getCurrName());
        dsUser.setModTime(LocalDateTime.now());
        Integer state = usersMapper.updateCoach(dsUser);
        System.out.println(dsUser);
        //出参
        if (state > 0) {
            return RestResponse.success("修改教练员信息成功！");
        } else {
            return RestResponse.error("修改教练员信息失败！");
        }
    }

    /**
     * 学员信息一览
     *
     * @param getStuViewForm
     * @return
     */
    @Override
    public List getStu(GetStuViewForm getStuViewForm) {
        //TODO 添加权限模块 教练员只能查看自己学员，管理员可以查看所有信息
        DSUser dsUser = new DSUser();
        dsUser.setUserName(getStuViewForm.getUserName());
        dsUser.setMobilePhone(getStuViewForm.getMobilePhone());
        dsUser.setIdCard(getStuViewForm.getIdCard());
        dsUser.setStatus(getStuViewForm.getStatus());
        List<DSUser> reDsUser = usersMapper.getStu(dsUser);
        List<GetStuInfoVO> getStuInfoVOList = new ArrayList<>(16);
        reDsUser.forEach(e -> {
            GetStuInfoVO getStuInfoVO = new GetStuInfoVO();
            getStuInfoVO.setGuid(e.getGuid());
            getStuInfoVO.setUserName(e.getUserName());
            getStuInfoVO.setRole(e.getRole());
            getStuInfoVO.setMobilePhone(e.getMobilePhone());
            getStuInfoVO.setIdCard(e.getIdCard());
            getStuInfoVO.setStatus(e.getStatus());
            getStuInfoVOList.add(getStuInfoVO);
        });
        return getStuInfoVOList;
    }


}
