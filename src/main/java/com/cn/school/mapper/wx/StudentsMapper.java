package com.cn.school.mapper.wx;

import com.cn.school.entity.DSStudents;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
    Integer addStudents(@Param("dsStudents") DSStudents dsStudents);

}
