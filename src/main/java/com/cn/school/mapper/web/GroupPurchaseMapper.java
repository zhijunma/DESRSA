package com.cn.school.mapper.web;

import com.cn.school.entity.DSGrpPurchase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
}
