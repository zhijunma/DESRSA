package com.cn.school.service.mzj.impl;

import com.cn.school.FormView.GetRSAViewForm;
import com.cn.school.entity.mzj.DSRSAPassword;
import com.cn.school.entity.mzj.DSRSAPrivateKey;
import com.cn.school.entity.mzj.DSRSAPublicKey;
import com.cn.school.mapper.mzj.CustomRSAMapper;
import com.cn.school.service.mzj.CustomRSAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import util.CustomRSAUtil;

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
    @Transactional
    public String addRSA(String word, Integer keySize) {
        CustomRSAUtil customRSAUtil = new CustomRSAUtil();
        DSRSAPassword dsrsa = new DSRSAPassword();
        DSRSAPrivateKey dsrsaPrivateKey = new DSRSAPrivateKey();
        DSRSAPublicKey dsrsaPublicKey = new DSRSAPublicKey();
        String encodedData = new String();
        //随机产生密钥对
        Map<String, String> keyMap = customRSAUtil.createKeys(keySize);
        String publicKey = keyMap.get("publicKey");
        String privateKey = keyMap.get("privateKey");
        try {
            encodedData = customRSAUtil.publicEncrypt(word, customRSAUtil.getPublicKey(publicKey));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        //缓存密文
        dsrsa.setPassWord(encodedData);
        Integer state = customRSAMapper.addRSA(dsrsa);
        if (state <= 0) {
            throw new RuntimeException("添加失败");
        } else {
            //缓存要存放的公钥
            dsrsaPublicKey.setRsaId(dsrsa.getId());
            dsrsaPublicKey.setPublicKey(publicKey);
            Integer sta = customRSAMapper.addRSAPublicKey(dsrsaPublicKey);
            if (sta <= 0) {
                throw new RuntimeException("添加失败");
            } else {
                //缓存要存放的私钥
                dsrsaPrivateKey.setRsaId(dsrsa.getId());
                dsrsaPrivateKey.setPrivateKey(privateKey);
                Integer st = customRSAMapper.addRSAPrivateKey(dsrsaPrivateKey);
                if (st <= 0) {
                    throw new RuntimeException("添加失败");
                } else {

                    return "添加成功！";
                }
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
        return decodedData;
    }
    /**
     * 通过id获取密文
     * @param form
     * @return
     */
    @Override
    public String getRSAPasswordById(GetRSAViewForm form) {
        DSRSAPassword ds = customRSAMapper.getRSAPasswordById(form.getId());
        //判断是否存在密文
        if (ObjectUtils.isEmpty(ds)) {
            throw new RuntimeException("数据不存在！");
        } else {
            return ds.getPassWord();
        }
    }
    /**
     * 通过id获取公钥
     * @param form
     * @return
     */
    @Override
    public String getRSAPublicKeyById(GetRSAViewForm form) {
        DSRSAPublicKey ds = customRSAMapper.getRSAPublicKeyById(form.getRsaId());
        //判断是否存在密文
        if (ObjectUtils.isEmpty(ds)) {
            throw new RuntimeException("数据不存在！");
        } else {
            return ds.getPublicKey();
        }
    }
    /**
     * 通过id获取私钥
     * @param form
     * @return
     */
    @Override
    public String getRSAPrivateKeyById(GetRSAViewForm form) {
        DSRSAPrivateKey ds = customRSAMapper.getRSAPrivateKeyById(form.getRsaId());
        //判断是否存在密文
        if (ObjectUtils.isEmpty(ds)) {
            throw new RuntimeException("数据不存在！");
        } else {
            return ds.getPrivateKey();
        }
    }
}
