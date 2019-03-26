package com.cn.school.service.web.impl;
import com.cn.school.dto.info.bo.AddOrderBO;
import com.cn.school.dto.info.vo.GetOrderInfoVO;
import com.cn.school.entity.DSOrder;
import com.cn.school.mapper.web.OrderManageMapper;
import com.cn.school.service.web.OrderManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:mzj data:2019/3/25
 * 18:00
 */
@Slf4j
@Service
public class OrderManageServiceImpl implements OrderManageService {

    @Autowired
    private OrderManageMapper orderManageMapper;


    /**
     * 添加订单
     * @param addOrderBO
     * @return
     */
    @Override
   public List getOrders(AddOrderBO addOrderBO) {

        DSOrder dsOrder = new DSOrder();
        dsOrder.setStatus(addOrderBO.getStatus());
         List<DSOrder> reDeOrder = orderManageMapper.getOrders(dsOrder);
         List<GetOrderInfoVO> getOrderInfoVOS = new ArrayList<>(16);
         //缓存信息到VO中
         reDeOrder.forEach(e -> {
              GetOrderInfoVO getOrderInfoVO = new GetOrderInfoVO();
              getOrderInfoVO.setGuid(e.getGuid());
              getOrderInfoVO.setService(e.getService());
              getOrderInfoVO.setVersion(e.getVersion());
              getOrderInfoVO.setCharset(e.getCharset());
              getOrderInfoVO.setSignType(e.getSignType());
              getOrderInfoVO.setMchId(e.getMchId());
              getOrderInfoVO.setIsRaw(e.getIsRaw());
              getOrderInfoVO.setIsMinipg(e.getIsMinipg());
              getOrderInfoVO.setOutTradeNo(e.getOutTradeNo());
              getOrderInfoVO.setDeviceInfo(e.getDeviceInfo());
              getOrderInfoVO.setOpUserId(e.getOpUserId());
              getOrderInfoVO.setOpShopId(e.getOpShopId());
              getOrderInfoVO.setBody(e.getBody());
              getOrderInfoVO.setSubOpenid(e.getSubOpenid());
              getOrderInfoVO.setSubAppid(e.getSubAppid());
              getOrderInfoVO.setAttach(e.getAttach());
              getOrderInfoVO.setTotalFee(e.getTotalFee());
              getOrderInfoVO.setNeedReceipt(e.getNeedReceipt());
              getOrderInfoVO.setMchCreateIp(e.getMchCreateIp());
              getOrderInfoVO.setNotifyUrl(e.getNotifyUrl());
              getOrderInfoVO.setTimeStart(e.getTimeStart());
              getOrderInfoVO.setTimeExpire(e.getTimeExpire());
              getOrderInfoVO.setGoodsTag(e.getGoodsTag());
              getOrderInfoVO.setNonceStr(e.getNonceStr());
              getOrderInfoVO.setLimitCreditPay(e.getLimitCreditPay());
              getOrderInfoVO.setSign(e.getSign());
              getOrderInfoVO.setStatus(e.getStatus());

              getOrderInfoVO.setAddUser(e.getAddUser());
              getOrderInfoVO.setAddUserId(e.getAddUserId());
              getOrderInfoVO.setAddTime(e.getAddTime());
              getOrderInfoVO.setModUser(e.getModUser());
              getOrderInfoVO.setModUserId(e.getModUserId());
              getOrderInfoVO.setModTime(e.getModTime());
              //将缓存的信息放入list中
              getOrderInfoVOS.add(getOrderInfoVO);
         });
         //返回VOList中的说有数据
         return getOrderInfoVOS;

    }
}
