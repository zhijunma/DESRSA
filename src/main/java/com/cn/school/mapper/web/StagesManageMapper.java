package com.cn.school.mapper.web;

import com.cn.school.entity.DSUser;
import com.cn.school.entity.DSstages;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository

public interface StagesManageMapper {


    /**
     * 添加分期活动
     *
     * @param dsstages
     * @return
     */
    Integer addStages(@Param("dsstages") DSstages dsstages);
}
