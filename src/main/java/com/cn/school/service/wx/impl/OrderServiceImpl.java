package com.cn.school.service.wx.impl;
import com.cn.school.dto.info.bo.AddOrderBO;
import com.cn.school.entity.DSOrder;
import com.cn.school.mapper.wx.OrderMapper;
import com.cn.school.service.wx.OrderService;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author:mzj data:2019/3/25
 * 18:00
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;


    /**
     * 添加订单
     * @param addOrderBO
     * @return
     */
    @Override
   public RestResponse addOrder(AddOrderBO addOrderBO) {

        DSOrder dsOrder = new DSOrder();
        //接口信息
        dsOrder.setService(addOrderBO.getService());
        //版本号
        dsOrder.setVersion(addOrderBO.getVersion());
        //字符集
        dsOrder.setCharset(addOrderBO.getCharset());
        //签名方式
        dsOrder.setSignType(addOrderBO.getSignType());
        //商户号
        dsOrder.setMchId(addOrderBO.getMchId());
        //原生js
        dsOrder.setIsRaw(addOrderBO.getIsRaw());
        //是否小程序支付
        dsOrder.setIsMinipg(addOrderBO.getIsMinipg());
        //商户订单号
        dsOrder.setOutTradeNo(addOrderBO.getOutTradeNo());
        //设备号
        dsOrder.setDeviceInfo(addOrderBO.getDeviceInfo());
        //操作员
        dsOrder.setOpUserId(addOrderBO.getOpUserId());
        //门店标号
        dsOrder.setOpShopId(addOrderBO.getOpShopId());
        //商品描述
        dsOrder.setBody(addOrderBO.getBody());
        //用户openid
        dsOrder.setSubOpenid(addOrderBO.getSubOpenid());
        //公众号或小程序id
        dsOrder.setSubAppid(addOrderBO.getSubAppid());
        //附加消息
        dsOrder.setAttach(addOrderBO.getAttach());
        //总金额
        dsOrder.setTotalFee(addOrderBO.getTotalFee());
        //电子发票?
        dsOrder.setNeedReceipt(addOrderBO.getNeedReceipt());
        //终端IP
        dsOrder.setMchCreateIp(addOrderBO.getMchCreateIp());
        //通知地址
        dsOrder.setNotifyUrl(addOrderBO.getNotifyUrl());
        //订单开始时间
        dsOrder.setTimeStart(addOrderBO.getTimeStart());
        //订单过期时间
        dsOrder.setTimeExpire(addOrderBO.getTimeExpire());
        //商品标记
        dsOrder.setGoodsTag(addOrderBO.getGoodsTag());
        //随机字符串
        dsOrder.setNonceStr(addOrderBO.getNonceStr());
        //是否限制信用卡 0，否  1，是
        dsOrder.setLimitCreditPay(addOrderBO.getLimitCreditPay());
        //签名
        dsOrder.setSign(addOrderBO.getSign());
        //订单状态
         dsOrder.setStatus(addOrderBO.getStatus());
        //添加人
        dsOrder.setAddUserId(0L);
        dsOrder.setAddUser("待办...");
        dsOrder.setAddTime(LocalDateTime.now());
        //修改人
        dsOrder.setModUserId(0L);
        dsOrder.setModUser("待办...");
        dsOrder.setModTime(LocalDateTime.now());
        dsOrder.setDeleteFlag(false);
        //插入信息到分期优惠表主表
        Integer st = orderMapper.addOrder(dsOrder);
        if (st > 0) {
            return RestResponse.success("添加订单信息成功！");
        } else {
            throw new RuntimeException("添加订单信息失败！");
        }
    }
}
