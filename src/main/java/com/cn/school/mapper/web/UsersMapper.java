package com.cn.school.mapper.web;

import com.cn.school.entity.DSUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Mapper
@Repository
public interface UsersMapper {
    /**
     * 用户查看个人信息
     * @param guid
     * @return
     */
    DSUser getUser(@Param("guid") @NotNull(message = "手机号码不能为空") Long guid);

    /**
     * 用户修改个人信息
     * @param mobilePhone
     * @param password
     * @param guid
     * @return
     */
    DSUser updateUsers(@Param("guid") @NotNull(message = "手机号码不能为空") Long guid,
                       @Param("password") @NotBlank(message = "登录账号或密码不能为空") String password,@Param("mobilePhone") @NotBlank String mobilePhone);
}
