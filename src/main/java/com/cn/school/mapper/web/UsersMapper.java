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
    DSUser getUser(@Param("guid") @NotNull(message = "编号不能为空") Long guid);

    /**
     * 用户修改个人信息
     *
     * @param mobilePhone
     * @param password
     * @param guid
     * @return
     */
    Integer updateUsers(@Param("guid") @NotNull(message = "编号不能为空") Long guid,
                        @Param("password") String password, @Param("mobilePhone") String mobilePhone, @Param("idCard") String idCard);

    /**
     * 教练员添加，教练员信息只能由管理员添加
     *
     * @param dsUser
     * @return
     */
    Integer insertCoach(@Param("dsUser") DSUser dsUser);

    /**
     * 教练员信息一览
     *
     * @param dsUser
     * @return
     */
    List<DSUser> getCoachs(@Param("dsUser") DSUser dsUser);

    /**
     * 教练员删除,假删除（更新状态）
     *
     * @param dsUser
     * @return
     */
    Integer deleteCoach(@Param("dsUser") DSUser dsUser);

    /**
     * 教练员详情查看（根据身份证号查询教练员）
     *
     * @param dsUser
     * @return
     */
    DSUser getCoach(@Param("dsUser") DSUser dsUser);

    /**
     * 教练员信息修改（只能由管理员修改）
     *
     * @param dsUser
     * @return
     */
    Integer updateCoach(@Param("dsUser") DSUser dsUser);
    /**
     * 学员信息一览
     *
     * @param dsUser
     * @return
     */
    List<DSUser> getStu(@Param("dsUser") DSUser dsUser);

}
