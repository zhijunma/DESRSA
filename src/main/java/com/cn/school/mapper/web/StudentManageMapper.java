package com.cn.school.mapper.web;

import com.cn.school.entity.DSStudents;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface StudentManageMapper {

    /**
     * 教练员删除,假删除（更新状态）
     *
     * @param dsStudents
     * @return
     */
    Integer deleteStudent(@Param("dsStudents") DSStudents dsStudents);

    /**
     * 用户修改个人信息
     *
     * @param dsStudents
     * @return
     */
    Integer updateStudent(@Param("dsStudents") DSStudents dsStudents);

    /**
     * 查看学员信息（根据guid或id_card或username）
     *
     * @param dsStudents
     * @return
     */
    DSStudents getStudent(@Param("dsStudents") DSStudents dsStudents);

    /**
     * 学员信息一览
     *
     * @param dsStudents
     * @return
     * @author leiyunlong
     */
    List<DSStudents> getStudentList(@Param("dsStudents") DSStudents dsStudents);


}
