package com.cn.school.service.mzj;


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
}
