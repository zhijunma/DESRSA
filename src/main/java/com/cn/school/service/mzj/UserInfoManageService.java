package com.cn.school.service.mzj;


import com.cn.school.FormView.GetUserInfoViewForm;
import com.cn.school.entity.mzj.DSUserInfo;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserInfoManageService {
    /**
     * 通过guid获取用户信息
     * @param guid
     * @return
     */
    DSUserInfo getUserInfo(Long guid);

    /**
     * 修改信息
     * @param form
     * @return
     */
    String updateUserInfo(GetUserInfoViewForm form);
}
