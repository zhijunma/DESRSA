package com.cn.school.service.mzj;

import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author mzj
 * @date 2019-3-28 上午10:12:11
 */
@Transactional
public interface CustomDESService {
    /**
     * 添加原文和加密后的数据到数据库
     * @param word
     * @return
     */
    String addDES(String word,String key);

    /**
     * 通过id获取密文
     * @param id
     * @return
     */
    String getDESPassword(Long id);
    /**
     * 通过id获取key
     * @param id
     * @return
     */
    String getDESKey(Long id);

    /**
     * 通过id获取密文并进行解密
     * @param password
     * @param Key
     * @return
     */
    String getDESWord(String password,String Key);
}
