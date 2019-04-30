package com.cn.school.service.mzj;

import com.cn.school.FormView.GetRSAViewForm;
import com.cn.school.entity.mzj.DSRSA;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mzj Date:4.22
 * Time 19:44
 */
@Transactional
public interface CustomRSAService {
    /**
     * 添加原文和加密后的数据到数据库
     * @param word
     * @param keySize
     * @return
     */
    String addRSA(String word, Integer keySize);

    /**
     * 进行解密
     * @param form
     * @return
     */
    String getRSA(GetRSAViewForm form);
    /**
     * 通过id获取密文和密钥
     * @param form
     * @return
     */
    DSRSA getRSAPasswordKey(GetRSAViewForm form);
}
