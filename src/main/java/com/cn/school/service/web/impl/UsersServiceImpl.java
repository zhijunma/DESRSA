package com.cn.school.service.web.impl;

import com.cn.school.dto.forms.usermanage.*;
import com.cn.school.dto.info.vo.GetUserInfoVO;
import com.cn.school.entity.DSUser;
import com.cn.school.mapper.web.UsersMapper;
import com.cn.school.mapstruct.UserMapStruct;
import com.cn.school.service.web.UsersService;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserMapStruct userMapStruct;
    /**
     * 学员
     */
    private final Integer stuRole = 1;

    /**
     * 用户查看个人信息
     *
     * @param userViewForm
     * @return
     */
    @Override
    public RestResponse getUser(GetUserViewForm userViewForm) {
        DSUser dsUser = usersMapper.getUser(userViewForm.getGuid());
        GetUserInfoVO getUserInfoVO = new GetUserInfoVO();
        getUserInfoVO.setPassword(dsUser.getPassword());
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
        Integer state = usersMapper.updateUsers(userViewForm.getGuid(), userViewForm.getPassword(), userViewForm.getMobilePhone());
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
    public RestResponse insertCoach(InsertCoachViewForm insertCoachViewForm) {
        if (insertCoachViewForm.getCurrRole() != 3) {
            return RestResponse.error("权限不足！");
        }
        DSUser dsUser = new DSUser();
        dsUser.setUserName(insertCoachViewForm.getUserName());
        dsUser.setPassword(insertCoachViewForm.getPassword());
        dsUser.setMobilePhone(insertCoachViewForm.getMobilePhone());
        dsUser.setIdCard(insertCoachViewForm.getIdCard());
        dsUser.setRole(insertCoachViewForm.getRole());
        dsUser.setStatus(insertCoachViewForm.getStatus());
        dsUser.setAddUserId(insertCoachViewForm.getAddUserId());
        dsUser.setAddUser(insertCoachViewForm.getAddUser());
        dsUser.setAddTime(LocalDateTime.now());
        dsUser.setModUserId(insertCoachViewForm.getModUserId());
        dsUser.setModUser(insertCoachViewForm.getModUser());
        dsUser.setModTime(LocalDateTime.now());
        dsUser.setDeleteFlag(false);
        Integer state = usersMapper.insertCoach(dsUser);
        if (state > 0) {
            return RestResponse.success("添加教练员信息成功！");
        } else {
            return RestResponse.error("添加教练员信息失败！");
        }
    }

    /**
     * 教练员一览
     *
     * @param getCoachViewForm
     * @return
     */
    @Override
    public List getCoach(GetCoachViewForm getCoachViewForm) {
        DSUser dsUser = new DSUser();
        dsUser.setUserName(getCoachViewForm.getUserName());
        dsUser.setMobilePhone(getCoachViewForm.getMobilePhone());
        dsUser.setIdCard(getCoachViewForm.getIdCard());

        List<DSUser> reDsUser = usersMapper.getCoach(dsUser);
        List<GetUserInfoVO> getUserInfoVOList = new ArrayList<>(16);
        reDsUser.forEach(e -> {
            GetUserInfoVO getUserInfoVO = new GetUserInfoVO();
            getUserInfoVO.setUserName(e.getUserName());
            getUserInfoVO.setRole(e.getRole());
            getUserInfoVO.setMobilePhone(e.getMobilePhone());
            getUserInfoVO.setIdCard(e.getIdCard());
            getUserInfoVO.setStatus(e.getStatus());
            getUserInfoVOList.add(getUserInfoVO);
        });
        return getUserInfoVOList;
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
        dsUser.setModUserId(deleteCoachViewForm.getModUserId());
        dsUser.setModUser(deleteCoachViewForm.getModUser());
        dsUser.setModTime(LocalDateTime.now());
        Integer state = usersMapper.deleteCoach(dsUser);
        if (state > 0) {
            return RestResponse.success("删除教练员成功！");
        } else {
            return RestResponse.error("删除教练员失败！");
        }
    }

}
