package com.cn.school.mapper.mzj;

import com.cn.school.entity.mzj.DSGoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 * 条件获取商品信息
 */
@Mapper
@Repository
public interface GoodsInfoMapper {
    /**
     * 条件获取商品信息
     * @param ds
     * @return
     */
    List<DSGoodsInfo> getGoodsinfo(@Param("ds") DSGoodsInfo ds);
}
