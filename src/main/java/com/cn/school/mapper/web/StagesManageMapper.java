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
     *
     * @param dSstages
     * @return
     */
    Long addStages(@Param("dSstages") DSstages dSstages);

    /**
     * 添加分期活动子表
     *
     * @param dsStagesItem
     * @return
     */
    Integer addStagesItem(@Param("dsStagesItem") List<DSStagesItem> dsStagesItem);

    /**
     * 删除分期优惠主表（假删除）
     *
     * @param list
     * @param modUserId
     * @param modUser
     * @return
     */
    Integer deleteStages(@Param("list") List<Long> list, @Param("modUserId") Long modUserId, @Param("modUser") String modUser);

    /**
     * 删除分期优惠子表
     *
     * @param list
     * @param modUserId
     * @param modUser
     * @return
     */
    Integer deleteStagesItem(@Param("list") List<Long> list, @Param("modUserId") Long modUserId, @Param("modUser") String modUser);

    /**
     * 修改分期优惠主表
     *
     * @param dSstages
     * @return
     */
    Integer updateStages(@Param("dSstages") DSstages dSstages);

    /**
     * 移除分期优惠子表
     *
     * @param dSstages
     * @return
     */
    Integer moveStagesItem(@Param("dSstages") DSstages dSstages);

    /**
     * 启用分期活动
     * @param list
     * @param modUserId
     * @param modUser
     * @return
     */
    Integer updateStagesEnable(@Param("list") List<Long> list, @Param("modUserId") Long modUserId, @Param("modUser") String modUser);

    /**
     * 停用分期活动
     * @param list
     * @param modUserId
     * @param modUser
     * @return
     */
    Integer updateStagesUnEnable(@Param("list") List<Long> list, @Param("modUserId") Long modUserId, @Param("modUser") String modUser);

    /**
     * 查询分期活动List
     * 完成中
     *
     * @param dSstages
     * @return
     */
    List getStagesList(@Param("dSstages") DSstages dSstages);

    /**
     * 查询分期活动详情
     * 已完成
     *
     * @param dSstages
     * @return
     */
    DSstages getStagesInfo(@Param("dSstages") DSstages dSstages);

}