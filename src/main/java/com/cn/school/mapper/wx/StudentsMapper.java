package com.cn.school.mapper.wx;

import com.cn.school.entity.DSStudents;
import com.cn.school.entity.DSStudentsOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

/**
 * @author:HuMin Date:2019/3/1
 * Time:15:46
 */
@Mapper
@Repository
public interface StudentsMapper {

    /**
     * 学员报名
     *
     * @param dsStudents
     * @return
     */
    Integer addStudents(@Param("po") DSStudents dsStudents);

    /**
     * 添加报名信息
     * @param dsStudents
     * @return
     */
    Integer updateStudentInfo(@Param("po") DSStudents dsStudents);

}
