package com.cn.school.mapper.wx;

import com.cn.school.dto.info.vo.GetStudentOrderVO;
import com.cn.school.entity.DSStudents;
import com.cn.school.entity.DSStudentsOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: leiyunlong
 * @Date: 2019/4/1 11:00
 * @Version 1.0
 */
@Mapper
@Repository
public interface StudentsOrderMapper {
    Integer addStudentsOrder(@Param("po") DSStudentsOrder dsStudentsOrder);

    /**
     * 学院查看个人信息及缴费情况
     *
     * @param openId
     * @return
     */
    DSStudents getStudentOrderInfo(@Param("openId") String openId);

    /**
     * 根据学员id查询缴费情况
     *
     * @param guid
     * @return
     */
    List<GetStudentOrderVO> getStudentOrderByStudentsId(@Param("guid") Long guid);
}
