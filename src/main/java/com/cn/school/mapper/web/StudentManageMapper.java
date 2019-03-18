package com.cn.school.mapper.web;

import com.cn.school.entity.DSUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository

public interface StudentManageMapper {

    /**
     * 教练员删除,假删除（更新状态）
     *
     * @param dsUser
     * @return
     */
    Integer deleteStudent(@Param("dsUser") DSUser dsUser);

    /**
     * 用户修改个人信息
     *
     * @param dsUser
     * @return
     */
    Integer updateStudent(@Param("dsUser") DSUser dsUser);

    /**
     * 查看学员信息（根据guid或id_card或username）
     *
     * @param dsUser
     * @return
     */
    DSUser getStudent(@Param("dsUser") DSUser dsUser);


}
