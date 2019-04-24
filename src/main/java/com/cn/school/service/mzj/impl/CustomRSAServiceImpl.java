package com.cn.school.service.mzj.impl;

import com.cn.school.FormView.GetRSAViewForm;
import com.cn.school.FormView.form.GetRSAForm;
import com.cn.school.entity.mzj.DSRSA;
import com.cn.school.mapper.mzj.CustomRSAMapper;
import com.cn.school.service.mzj.CustomRSAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import util.CustomRSAUtil;
import util.response.RestResponse;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
@Slf4j
@Service
public class CustomRSAServiceImpl implements CustomRSAService {
    @Autowired
    CustomRSAMapper customRSAMapper;

    /**
     * 添加原文进行加密
     * @param word
     * @param keySize
     * @return
     */
    @Override
    public String addRSA(String word, Integer keySize) {
        String password = customRSAMapper.getPasswordByWord(word);
        CustomRSAUtil customRSAUtil = new CustomRSAUtil();
        DSRSA dsrsa = new DSRSA();
        String encodedData = new String();
        Map<String, String> keyMap = customRSAUtil.createKeys(keySize);
        String  publicKey = keyMap.get("publicKey");
        String  privateKey = keyMap.get("privateKey");
        try {
            encodedData = customRSAUtil.publicEncrypt(word,customRSAUtil.getPublicKey(publicKey));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        dsrsa.setWord(word);
        dsrsa.setPassword(encodedData);
        dsrsa.setPublicKey(publicKey);
        dsrsa.setPrivateKey(privateKey);
        if (!ObjectUtils.isEmpty(password)){
            Integer st = customRSAMapper.updatePassword(dsrsa);
            if (st <= 0) {
                throw new RuntimeException("添加失败");
            } else {
                return "添加成功";
            }
        } else {
            Integer state = customRSAMapper.addRSA(dsrsa);
            if (state <= 0) {
                throw new RuntimeException("添加失败");
            } else {
                return "添加成功";
            }
        }

    }

    /**
     * 根据密文和私钥进行解密
     * @param form
     * @return
     */
    @Override
    public String getRSA(GetRSAViewForm form) {
        String decodedData = new String();
        CustomRSAUtil customRSAUtil = new CustomRSAUtil();
        try {
            decodedData = customRSAUtil.privateDecrypt(form.getPassword(), customRSAUtil.getPrivateKey(form.getPrivateKey()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "密文是"+form.getPassword()+"解析后的原文是："+decodedData;
    }

    /**
     * 通过id获取密文和密钥
     * @param id
     * @return
     */
    @Override
    public DSRSA getRSAPasswordKey(Long id) {
        //通过id获取密文和密钥
        GetRSAForm form = customRSAMapper.getRSAById(id);
        //判断是否存在密文
        if (ObjectUtils.isEmpty(form)) {
            throw new RuntimeException("数据不存在！");
        } else {
            DSRSA dsrsa = new DSRSA();
            dsrsa.setPrivateKey(form.getPrivateKey());
            dsrsa.setPassword(form.getPassword());
            return dsrsa;
        }
    }
}
