package com.cn.school.mapper.web;

import com.cn.school.entity.DSGrpPurchase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupPurchaseMapper {

    /**
     * 管理员生成团购活动内容，内容信息只能由管理员添加
     *
     * @param dsGrpPurchase
     * @return
     */
    Integer addGroupPurchase(@Param("dsGrpPurchase") DSGrpPurchase dsGrpPurchase);

    /**
     * 全部团购活动一览
     *
     * @param dsGrpPurchase
     * @return
     */
    List<DSGrpPurchase> getGroupPurchaseList(@Param("dsGrpPurchase") DSGrpPurchase dsGrpPurchase);

    /**
     * 团购活动详细查看
     *
     * @param dsGrpPurchase
     * @return
     */
    DSGrpPurchase getGroupPurchase(@Param("dsGrpPurchase") DSGrpPurchase dsGrpPurchase);

    /**
     * 团购信息修改（只能由管理员修改）
     *
     * @param dsGrpPurchase
     * @return
     */
    Integer updateGroupPurchase(@Param("dsGrpPurchase") DSGrpPurchase dsGrpPurchase);

    /**
     * 团购信息单、批量删除（假删除）
     *
     * @param dsGrpPurchase
     * @return
     */
    Integer deleteGroupPurchase(@Param("dsGrpPurchase") List<DSGrpPurchase> dsGrpPurchase);
}
