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
     *管理员删除评价与投诉
     * @param dsEvaluate
     * @return
     */
    Integer deleteEvaluate(@Param("dsEvaluate") DSEvaluate dsEvaluate);

    /**
     *管理员浏览评价与投诉
     * @param dsEvaluate
     * @return
     */
    List<DSEvaluate> getEvaluates(@Param("dsEvaluate") DSEvaluate dsEvaluate);
    /**
     *管理员查看评价与投诉详细
     * @param dsEvaluate
     * @return
     */
    List<DSEvaluate> getEvaluate(@Param("dsEvaluate") DSEvaluate dsEvaluate);

    /**
     *学员添加评价与投诉
     * @param dsEvaluate
     * @return
     */
    Integer addEvaluate(@Param("dsEvaluate") DSEvaluate dsEvaluate);

    /**
     *学员浏览评价与投诉
     * @return
     */
    List<DSEvaluate> stuGetEvaluates();

}
