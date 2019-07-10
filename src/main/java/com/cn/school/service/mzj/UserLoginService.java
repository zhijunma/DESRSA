package com.cn.school.service.mzj;

import com.cn.school.FormView.GetUserInfoViewForm;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserLoginService {
    /**
     * 通过id获取密文
     * @param form
     * @return
     */
    String userLogin(GetUserInfoViewForm form);

    /**
     * 添加用户信息
     * @param form
     * @return
     */
    String addUserInfo(GetUserInfoViewForm form);
}
