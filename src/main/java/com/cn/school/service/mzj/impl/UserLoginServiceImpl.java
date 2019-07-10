package com.cn.school.service.mzj.impl;

import com.cn.school.FormView.GetUserInfoViewForm;
import com.cn.school.entity.mzj.DSUserInfo;
import com.cn.school.mapper.mzj.UserLoginMapper;
import com.cn.school.service.mzj.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    UserLoginMapper userLoginMapper;

    /**
     *用户登录
      * @param viewForm
     * @return
     */
    @Override
    public String userLogin(GetUserInfoViewForm viewForm) {
        //判断输入的账号密码是否为空
//        if (ObjectUtils.isEmpty(viewForm.getUserName())
//        || ObjectUtils.isEmpty(viewForm.getUserPassword())){
//            return "账号或密码不能为空";
//        }
        //用户信息缓存
        DSUserInfo ds = new DSUserInfo();
        ds.setUserName(viewForm.getUserName());
        ds.setMobilePhone(viewForm.getMobilePhone());
        ds.setUserPassword(viewForm.getUserPassword());
        DSUserInfo dsUserInfo = new DSUserInfo();

        //通过账号获取密码
        dsUserInfo.setUserPassword(userLoginMapper.getUserPassword(ds));
        //判断用户账号和密码是否为空，以及密码是否正确
        if (ObjectUtils.isEmpty(dsUserInfo.getUserPassword())
                || !dsUserInfo.getUserPassword().equals(ds.getUserPassword()))
        {
            return "账号或密码不正确！";
        } else {
            return "登录成功!";
        }
    }

    /**
     * 用户注册
     * @param form
     * @return
     */
    @Override
    public String addUserInfo(GetUserInfoViewForm form) {
        if (!ObjectUtils.isEmpty(form.getUserPassword()) &&
                !ObjectUtils.isEmpty(form.getUserName())&&
                !ObjectUtils.isEmpty(form.getMobilePhone())) {
            DSUserInfo ds = new DSUserInfo();
            ds.setUserPassword(form.getUserPassword());
            ds.setMobilePhone(form.getMobilePhone());
            ds.setUserName(form.getUserName());
            DSUserInfo dsUserInfo = userLoginMapper.getUserByMobilePhone(form.getMobilePhone());
            if (!ObjectUtils.isEmpty(dsUserInfo)) {
//                System.out.println("-----------");
                return "用户已存在";
            } else {
                Integer status = userLoginMapper.addUserInfo(ds);
                if (!ObjectUtils.isEmpty(status)) {
//                    System.out.println("+++++++++++");
                    return "注册成功";
                } else {
//                    System.out.println("============");
                    return "注册失败";
                }
            }
        } else {
            return "请输入完整的信息";
        }
    }
}
