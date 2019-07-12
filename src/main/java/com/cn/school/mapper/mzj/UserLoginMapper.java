package com.cn.school.mapper.mzj;

import com.cn.school.entity.mzj.DSUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserLoginMapper {
    /**
     * 通过用户昵称或者电话号码获取密码
     * @param ds
     * @return
     */
    DSUserInfo getUserPassword(@Param("ds") DSUserInfo ds);

    /**
     * 通过用户昵称或者电话号码获取密码
     * @param ds
     * @return
     */
    Integer addUserInfo(@Param("ds") DSUserInfo ds);

    /**
     * 查看用户是否存在
     * @param guid
     * @return
     */
    DSUserInfo getUserByMobilePhone(@Param("guid") Long guid);


}
