package com.cn.school.mapper.wx;

import com.cn.school.entity.DSStudentsOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
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
     * @param idCard
     * @param userName
     * @return
     */
    List<DSStudentsOrder> getStudentOrderInfo(@Param("idCard") String idCard,
                                              @Param("userName")  String userName,
                                              @Param("openId") @NotBlank(message = "openId不能为空") String openId);
}
