package com.cn.school.service.mzj.impl;

import com.cn.school.entity.mzj.DSDESKey;
import com.cn.school.entity.mzj.DSDESPassword;
import com.cn.school.mapper.mzj.CustomDESMapper;
import com.cn.school.service.mzj.CustomDESService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import util.CustomDESUtil;
/**
 *
 * @author mzj
 * @date 2015-3-17 上午10:12:11
 */
@Slf4j
@Service
public class CustomDESImpl implements CustomDESService {
    @Autowired
    private CustomDESMapper customDESMapper;

    /**
     * 对输入的word进行加密并存入数据库
     * @param word
     * @param k
     * @return
     */
    @Override
    @Transactional
    public String addDES(String word,String k) {
        String password = new String();
        CustomDESUtil customDES = new CustomDESUtil();
        try {
            //对word进行加密
            password = customDES.encrypt(word,k);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DSDESPassword dsMyDES = new DSDESPassword();
        DSDESKey dsdesKey = new DSDESKey();
            dsdesKey.setK(k);
            dsMyDES.setPassWord(password);
            //将加密后的密文与原文一起存放到数局库中
            Integer state = customDESMapper.addDES(dsMyDES);
            if (state <= 0) {
                throw new RuntimeException("添加失败");
            } else {
                dsdesKey.setDesId(dsMyDES.getId());
                //将加密后的密文id与key一起存放到数局库中
                Integer sta = customDESMapper.addDESKey(dsdesKey);
                if (sta <= 0) {
                    throw new RuntimeException("添加失败");
                } else {
                    return "添加成功";
                }
            }
    }
    //通过id获取加密后的密文
    @Override
    public String getDESPassword(Long id) {
        //通过id获取密文
        DSDESPassword dsMyDES = customDESMapper.getPasswordById(id);
        //判断是否存在密文
        if (ObjectUtils.isEmpty(dsMyDES)){
            return ("数据不存在！");
        } else {
            return dsMyDES.getPassWord();
        }

    }

    /**
     * 通过密文id获取key
     * @param id
     * @return
     */
    @Override
    public String getDESKey(Long id) {
        //通过id获取密文
        DSDESKey dsMyDES = customDESMapper.getKeyByDesId(id);
        //判断是否存在密文
        if (ObjectUtils.isEmpty(dsMyDES)){
            return ("数据不存在！");
        } else {
            return dsMyDES.getK();
        }
    }

    /**
     * 对密文进行转译
     * @param password
     * @param key
     * @return
     */
    @Override
    public String getDESWord(String password,String key) {
        CustomDESUtil customDES = new CustomDESUtil();
        String word = new String();
        try {
            word = customDES.decrypt(password,key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return word;
    }

}
