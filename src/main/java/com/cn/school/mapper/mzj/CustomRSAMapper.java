package com.cn.school.mapper.mzj;


import com.cn.school.entity.mzj.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * @author mzj Date:4.22
 * Time 19:44
 */
@Mapper
@Repository
public interface CustomRSAMapper {
    /**
     * 添加加密后的数据数据库
     * @param dsRSA
     * @return
     */
    Integer addRSA(@Param("dsRSA") DSRSAPassword dsRSA);
    /**
     * 添加私钥到数据库
     * @param ds
     * @return
     */
    Integer addRSAPrivateKey(@Param("ds") DSRSAPrivateKey ds);
    /**
     * 添加公钥到数据库
     * @param ds
     * @return
     */
    Integer addRSAPublicKey(@Param("ds") DSRSAPublicKey ds);

    /**
     * 通过id获取密文
     * @param id
     * @return
     */
    DSRSAPassword getRSAPasswordById(@Param("id") Long id);
    /**
     * 通过id获取PublicKeyById
     * @param rsaId
     * @return
     */
    DSRSAPublicKey getRSAPublicKeyById(@Param("rsaId") Long rsaId);
    /**
     * 通过id获取PrivateKey
     * @param rsaId
     * @return
     */
    DSRSAPrivateKey getRSAPrivateKeyById(@Param("rsaId") Long rsaId);
}
