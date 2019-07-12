package com.cn.school.service.mzj.impl;

import com.cn.school.FormView.GetUserInfoViewForm;
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
        DSUserInfo dsUserInfo = userInfoManageMapper.getUserInfoByGuid(guid);

        return dsUserInfo;
    }

    /**
     * 修改信息实现类
     * @param form
     * @return
     */
    @Override
    public String updateUserInfo(GetUserInfoViewForm form) {
        //新建一个对象用来缓存信息
        DSUserInfo dsUserInfo = new DSUserInfo();
        //读取前台传来的值
        dsUserInfo.setGuid(form.getGuid());
        dsUserInfo.setUserName(form.getUserName());
        dsUserInfo.setMobilePhone(form.getMobilePhone());
        dsUserInfo.setAddress(form.getAddress());
        dsUserInfo.setEmail(form.getEmail());
        dsUserInfo.setQq(form.getQq());
        //执行修改方法
        Integer sta = userInfoManageMapper.updateUserInfo(dsUserInfo);
        //判断是否修改成功
        if (sta <= 0){
            return "修改失败!";
        } else {
            return "修改成功！";
        }

    }


}
