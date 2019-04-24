package com.cn.school.service.mzj.impl;

import com.cn.school.entity.mzj.DSMyDES;
import com.cn.school.mapper.mzj.CustomDESMapper;
import com.cn.school.service.mzj.CustomDESService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    //对输入的word进行加密并存入数据库
    @Override
    public String addDES(String word,String k) {
        String password = new String();
        CustomDESUtil customDES = new CustomDESUtil();
        try {
            //对word进行加密
            password = customDES.encrypt(word,k);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //判断密文是否已经存在，如果是则改为更新密文
        String exits = customDESMapper.getPasswordByWord(word);
        if (!ObjectUtils.isEmpty(exits)) {
            //对密文进行更新
            Integer ss = customDESMapper.updatePassword(word, password);
            if (ss <= 0) {
                throw new RuntimeException("添加失败");
            } else {
                return "添加成功";
            }
        } else {
            DSMyDES dsMyDES = new DSMyDES();
            dsMyDES.setWord(word);
            dsMyDES.setK(k);
            dsMyDES.setPass(password);
            //将加密后的密文与原文一起存放到数局库中
            Integer state = customDESMapper.addDES(dsMyDES);
            if (state <= 0) {
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
        DSMyDES dsMyDES = customDESMapper.getPasswordById(id);
        //判断是否存在密文
        if (ObjectUtils.isEmpty(dsMyDES)){
            return ("数据不存在！");
        } else {
            return "编号是"+id+"的"+"密文是"+dsMyDES.getPass();
        }

    }

    @Override
    public String getDESKey(Long id) {
        //通过id获取密文
        DSMyDES dsMyDES = customDESMapper.getKeyById(id);
        //判断是否存在密文
        if (ObjectUtils.isEmpty(dsMyDES)){
            return ("数据不存在！");
        } else {
            return "编号是"+id+"的"+"key是"+dsMyDES.getK();
        }
    }

    @Override
    public String getDESWord(String password,String key) {
        CustomDESUtil customDES = new CustomDESUtil();
        String word = new String();
        try {
            word = customDES.decrypt(password,key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "原文是"+word;
    }

}
