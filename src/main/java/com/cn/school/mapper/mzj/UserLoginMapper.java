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
    public String getUserPassword(@Param("ds") DSUserInfo ds);

    /**
     * 通过用户昵称或者电话号码获取密码
     * @param ds
     * @return
     */
    public Integer addUserInfo(@Param("ds") DSUserInfo ds);
    /**
     * 查看用户是否存在
     * @return
     */
    public DSUserInfo getUserByMobilePhone(Long mobilePhone);


}
