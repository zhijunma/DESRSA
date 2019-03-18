package com.cn.school.mapper.web;

import com.cn.school.entity.DSEvaluate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface EvaluateManageMapper {

    /**
     *
     * @param dsEvaluate
     * @return
     */
    Integer deleteEvaluate(@Param("dsEvaluate") DSEvaluate dsEvaluate);

    /**
     *
     * @param dsEvaluate
     * @return
     */
    List<DSEvaluate> getEvaluates(@Param("dsEvaluate") DSEvaluate dsEvaluate);


}
