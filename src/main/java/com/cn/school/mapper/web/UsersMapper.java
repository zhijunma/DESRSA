package com.cn.school.mapper.web;

import com.cn.school.entity.DSUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Mapper
@Repository
public interface UsersMapper {
    /**
     * 用户查看个人信息
     *
     * @param guid
     * @return
     */
    DSUser getUser(@Param("guid") @NotNull(message = "手机号码不能为空") Long guid);

    /**
     * 用户修改个人信息
     *
     * @param mobilePhone
     * @param password
     * @param guid
     * @return
     */
    Integer updateUsers(@Param("guid") @NotNull(message = "编号不能为空") Long guid,
                        @Param("password") String password, @Param("mobilePhone") String mobilePhone);

    /**
     * 教练员添加，教练员信息只能由管理员添加
     *
     * @param dsUser
     * @return
     */
    Integer insertCoach(@Param("dsUser") DSUser dsUser);

    /**
     * 教练员一览
     *
     * @param dsUser
     * @return
     */
    List<DSUser> getCoach(@Param("dsUser") DSUser dsUser);

}
