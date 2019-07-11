package com.cn.school.service.mzj.impl;

import com.cn.school.entity.mzj.DSUserInfo;
import com.cn.school.mapper.mzj.UserInfoManageMapper;
import com.cn.school.service.mzj.UserInfoManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserInfoManageServiceImpl implements UserInfoManageService {
    @Autowired
    UserInfoManageMapper userInfoManageMapper;

    /**
     * 获取用户信息
     * @param guid
     * @return
     */
    @Override
    public DSUserInfo getUserInfo(Long guid) {
        DSUserInfo dsUserInfo = userInfoManageMapper.getUserInfo(guid);

        return dsUserInfo;
    }

//    @Override
//    public Integer updateUserInfo(DSUserInfo ds) {
//        return null;
//    }

}
