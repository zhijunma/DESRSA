package com.cn.school.mapper.web;


import com.cn.school.entity.DSStagesItem;
import com.cn.school.entity.DSstages;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


@Mapper
@Repository
public interface StagesManageMapper {


    /**
     * 添加分期活动
     * @param dSstages
     * @return
     */
    Long addStages(@Param("dSstages") DSstages dSstages);
    /**
     * 添加分期活动子表
     * @param dsStagesItem
     * @return
     */
    Integer addStagesItem(@Param("dsStagesItem") List<DSStagesItem> dsStagesItem);

    /**
     * 删除分期优惠主表（假删除）
     *
     * @param dSstages
     * @return
     */
    Integer deleteStages(@Param("dSstages") DSstages dSstages);
    /**
     * 删除分期优惠子表
     *
     * @param dSstages
     * @return
     */
    Integer deleteStagesItem(@Param("dSstages") DSstages dSstages);

    /**
     * 修改分期优惠主表
     * @param dSstages
     * @return
     */
    Integer updateStages(@Param("dSstages") DSstages dSstages);
    /**
     * 移除分期优惠子表
     * @param dSstages
     * @return
     */
    Integer moveStagesItem(@Param("dSstages")DSstages dSstages);
    /**
     * 启用分期活动
     *已完成
     * @param dSstages
     * @return
     */
    Integer updateStagesEnable(@Param("dSstages") DSstages dSstages);

    /**
     * 停用分期活动
     *已完成
     * @param dSstages
     * @return
     */
    Integer updateStagesUnEnable(@Param("dSstages") DSstages dSstages);

    /**
     * 查询分期活动List
     *完成中
     * @param dSstages
     * @return
     */
    List getStagesList(@Param("dSstages") DSstages dSstages);

    /**
     * 查询分期活动详情
     *已完成
     * @param dSstages
     * @return
     */
    DSstages getStagesInfo(@Param("dSstages") DSstages dSstages);

}
