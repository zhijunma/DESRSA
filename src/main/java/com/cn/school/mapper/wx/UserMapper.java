package com.cn.school.mapper.wx;

import com.cn.school.dto.info.po.InsertUserPO;
import com.cn.school.entity.DSUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author:HuMin Date:2019/3/1
 * Time:15:46
 */
@Mapper
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
}
