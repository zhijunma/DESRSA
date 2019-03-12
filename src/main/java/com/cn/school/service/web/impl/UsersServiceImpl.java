package com.cn.school.service.web.impl;

import com.cn.school.dto.forms.usermanage.GetUserViewForm;
import com.cn.school.dto.info.vo.GetUserInfoVO;
import com.cn.school.entity.DSUser;
import com.cn.school.mapper.web.UsersMapper;
import com.cn.school.mapstruct.UserMapStruct;
import com.cn.school.service.web.UsersService;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
     * 查看个人信息
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

    @Override
    public RestResponse updateUsers(GetUserViewForm userViewForm) {
        DSUser dsUser = usersMapper.updateUsers(userViewForm.getGuid(),userViewForm.getPassword(),userViewForm.getMobilePhone());
        GetUserInfoVO getUserInfoVO = new GetUserInfoVO();
        getUserInfoVO.setPassword(dsUser.getPassword());
        getUserInfoVO.setIdCard(dsUser.getIdCard());
        getUserInfoVO.setMobilePhone(dsUser.getMobilePhone());
        getUserInfoVO.setRole(dsUser.getRole());
        getUserInfoVO.setStatus(dsUser.getStatus());
        getUserInfoVO.setUserName(dsUser.getUserName());
        return RestResponse.success(getUserInfoVO);
    }
}
