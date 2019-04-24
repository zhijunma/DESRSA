package com.cn.school.mapper.mzj;

import com.cn.school.entity.mzj.DSMyDES;
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
    Integer addDES(@Param("dsMyDES")DSMyDES dsMyDES);
    /**
     * 通过原文获取密文
     * @param word
     * @return
     */
    String getPasswordByWord(@Param("word") String word);
    /**
     * 通过id获取密文
     * @param id
     * @return
     */
    DSMyDES getPasswordById(@Param("id") Long id);
    /**
     * 通过id获取Key
     * @param id
     * @return
     */
    DSMyDES getKeyById(@Param("id") Long id);
    /**
     * 通过原文修改密文
     * @param word
     * @return
     */
    Integer updatePassword(@Param("word") String word,@Param("password") String password);

}
