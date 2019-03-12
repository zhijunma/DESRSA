package com.cn.school.mapper.wx;

import com.cn.school.dto.info.po.InsertUserPO;
import com.cn.school.entity.DSUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author:HuMin Date:2019/3/1
 * Time:15:46
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 注册（插入用户）
     *
     * @param po
     * @return
     */
    Integer insertUser(@Param("po") InsertUserPO po);

    /**
     * 注册（插入用户）
     *
     * @param po
     * @return
     */
    Integer updateTime(@Param("dsUser") DSUser dsUser);

    /**
     * 根据手机号查询
     *
     * @param mobilePhone
     * @return
     */
    DSUser getUserInfoByTel(@Param("mobilePhone") String mobilePhone);

    /**
     * 登录验证
     *
     * @param userName,passWord
     * @return
     */
    DSUser login(@Param("username") String userName, @Param("password") String password);
}
