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
    Integer addStudents(@Param("po") DSStudents dsStudents);

    /**
     * 添加报名信息
     *
     * @param dsStudents
     * @return
     */
    Integer updateStudentInfo(@Param("po") DSStudents dsStudents);

    /**
     * 查询电话号码,openid,身份证号是否使用
     *
     * @param mobilePhone
     * @param openId
     * @param idCard
     * @return
     */
    Integer getMobileOnly(@Param("mobilePhone") String mobilePhone, @Param("openId") String openId, @Param("idCard") String idCard);

    /**
     * 修改学生表支付状态
     * @param guid
     * @return
     */
    Integer updateStudentStatus( @Param("guid") Long guid);

}
