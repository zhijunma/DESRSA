package com.cn.school.service.mzj.impl;

import com.cn.school.FormView.AddUserOrderViewForm;
import com.cn.school.entity.mzj.DSOrders;
import com.cn.school.mapper.mzj.OrdersManageMapper;
import com.cn.school.service.mzj.OrdersManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

/**
 * 订单实现类
 * @author Administrator
 */
@Slf4j
@Service
public class OrdersManageServiceImpl implements OrdersManageService {
    @Autowired
    OrdersManageMapper mapper;

    /**
     * 添加订单业务
     * @param form
     * @return
     */
    @Override
    public String addUserOrder(AddUserOrderViewForm form) {
        DSOrders ds = new DSOrders();
        //将前台的数据缓存
        ds.setGoodsGuid(form.getGoodsGuid());
        ds.setAddUserGuid(form.getAddUserGuid());
        ds.setQuantity(form.getQuantity());
        ds.setTotal(form.getTotal());
        ds.setAddTime(LocalDateTime.now());
        //执行添加操作 并获得添加后的guid
        Long sta = mapper.addUserOrder(ds);
        if (!ObjectUtils.isEmpty(sta)){
            return "添加成功！";
        } else {
            return "添加失败";
        }

    }
}
