package com.cn.school.mapper.mzj;

import com.cn.school.entity.mzj.DSDESKey;
import com.cn.school.entity.mzj.DSDESPassword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 *
 * @author mzj
 * @date 2019-3-28 上午10:12:11
 */
@Mapper
@Repository
public interface CustomDESMapper {
    /**
     * 添加原文和加密后的数据到数据库
     * @param dsMyDES
     * @return
     */
    Integer addDES(@Param("dsMyDES")DSDESPassword dsMyDES);
    /**
     * 添加原文和加密后的数据到数据库
     * @param ds
     * @return
     */
    Integer addDESKey(@Param("ds")DSDESKey ds);
    /**
     * 通过id获取密文
     * @param id
     * @return
     */
    DSDESPassword getPasswordById(@Param("id") Long id);
    /**
     * 通过id获取Key
     * @param desId
     * @return
     */
    DSDESKey getKeyByDesId(@Param("desId") Long desId);

}
