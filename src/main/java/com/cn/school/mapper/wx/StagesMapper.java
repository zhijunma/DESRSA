package com.cn.school.mapper.wx;

import com.cn.school.entity.DSStages;
import com.cn.school.entity.DSStagesItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:HuMin Date:2019/3/28
 * Time:9:13
 */
@Mapper
@Repository
public interface StagesMapper {

    /**
     * 根据班级查询分期活动
     *
     * @param costId
     * @return
     */
    List<DSStages> getStagesListByCost(@Param("costId") Long costId);

    /**
     * 根据guid查看分期活动详情
     *
     * @param guid
     * @return
     */
    List<DSStagesItem> getStagesInfoByGuid(@Param("guid") Long guid);
}
