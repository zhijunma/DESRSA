package com.cn.school.mapper.web;

import com.cn.school.entity.DSCostInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:HuMin Date:2019/3/20
 * Time:11:13
 */

@Mapper
@Repository
public interface CostInfoManageMapper {

    /**
     * 学员报名
     *
     * @return
     */
    List<DSCostInfo> getCostInfoList();

    /**
     * 添加报名费用
     *
     * @param dsCostInfo
     * @return
     */
    Integer addCostInfo(@Param("dsCostInfo") DSCostInfo dsCostInfo);

    /**
     * 修改报名费用
     *
     * @param dsCostInfo
     * @return
     */
    Integer updateCostInfo(@Param("dsCostInfo") DSCostInfo dsCostInfo);

    /**
     * 删除报名费用
     *
     * @param list
     * @param modUserId
     * @param modUser
     * @return
     */
    Integer deleteCostInfo(@Param("list") List<Long> list, @Param("modUserId") Long modUserId,
                           @Param("modUser") String modUser);
}
