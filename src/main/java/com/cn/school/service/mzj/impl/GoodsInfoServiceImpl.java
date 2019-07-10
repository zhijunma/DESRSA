package com.cn.school.service.mzj.impl;

import com.cn.school.FormView.GetGoodsViewForm;
import com.cn.school.entity.mzj.DSGoodsInfo;
import com.cn.school.mapper.mzj.GoodsInfoMapper;
import com.cn.school.service.mzj.GoodsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
    @Autowired
    GoodsInfoMapper mapper;

    /**
     * 查询商品信息  条件可选
     * @param form
     * @return
     */
    @Override
    public List<DSGoodsInfo> getGoodsInfo(GetGoodsViewForm form) {
        DSGoodsInfo ds = new DSGoodsInfo();
        ds.setGoodsName(form.goodsName);
        ds.setPrice(form.price);
        ds.setRecommend(form.recommend);
        ds.setType(form.type);
        ds.setIsNew(form.isNew);
        //从数据库中获取信息
        List<DSGoodsInfo> goodsInfoList = mapper.getGoodsinfo(ds);
        return goodsInfoList;
    }
}
