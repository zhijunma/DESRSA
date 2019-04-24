package com.cn.school.mapper.mzj;

import com.cn.school.FormView.form.GetRSAForm;
import com.cn.school.entity.mzj.DSRSA;
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
     * 添加原文和加密后的数据以及公钥、私钥到数据库
     * @param dsRSA
     * @return
     */
    Integer addRSA(@Param("dsRSA")DSRSA dsRSA);
    /**
     * 通过原文获取密文
     * @param word
     * @return
     */
    String getPasswordByWord(@Param("word") String word);
    /**
     * 通过原文获取密文和秘钥
     * @param id
     * @return
     */
    GetRSAForm getRSAById(@Param("id") Long id);
    /**
     * 通过原文修改密文
     * @param dsRSA
     * @return
     */
    Integer updatePassword(@Param("dsRSA")DSRSA dsRSA);

}
