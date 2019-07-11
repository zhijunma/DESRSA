package com.cn.school.mapper.mzj;

import com.cn.school.entity.mzj.DSUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Mapper
@Repository
public interface UserInfoManageMapper {

    /**
     * @Prame guid
     * 通过guid获取用户信息
     */
    DSUserInfo getUserInfo(@Param("guid") Long guid);

    /**
     * 更新用户信息
     * @param ds
     * @return
     */
    Integer updateUserInfo(@Param("ds") DSUserInfo ds);
}
