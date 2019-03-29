package com.cn.school.controller;

import com.cn.school.entity.DSOrder;
import com.cn.school.mapper.wx.OrderMapper;
import com.cn.school.utils.pay.SignUtils;
import com.cn.school.utils.pay.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <一句话功能简述> <功能详细描述>通知
 *
 * @author Administrator
 * @version [版本号, 2014-8-28]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@WebServlet(name = "TestPayResultSerlet", urlPatterns = "/pay/notification")
@Slf4j
public class TestPayResultSerlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            log.debug("收到通知...");
            System.out.println("收到通知...");
            req.setCharacterEncoding("utf-8");
            resp.setHeader("Content-type", "text/html;charset=UTF-8");
            String resString = XmlUtils.parseRequst(req);
            log.debug("请求的内容：" + resString);
            System.out.println("请求的内容：" + resString);

            String respString = "error";
            if (resString != null && !"".equals(resString)) {
                Map<String, String> map = XmlUtils.toMap(resString.getBytes(), "utf-8");
                String res = XmlUtils.toXml(map);
                log.debug("请求结果：" + res);
                System.out.println("请求结果：" + res);
                //获取传过来的订单号out_trade_no和订单金额total_fee
                String outTradeNo = map.get("out_trade_no");
                Integer totalFee = Integer.valueOf(map.get("total_fee"));
                String bankType = map.get("bank_type");
                DSOrder dsOrder = new DSOrder();
                dsOrder.setOutTradeNo(outTradeNo);
                dsOrder.setTotalFee(totalFee);
                DSOrder order = orderMapper.getOrder(dsOrder);
                System.out.println("数据库里的金额" + order.getTotalFee());
                System.out.println("传过来的金额" + totalFee);
                System.out.println("guidshiduoshao" + order.getGuid());
                if (order == null) {
                    System.out.println("没有相关订单");
                    throw new RuntimeException("没有相关订单");
                } else if (!order.getTotalFee().equals(totalFee)) {
                    System.out.println("订单金额不正确");
                    throw new RuntimeException("订单金额不正确");
                } else {
                    //修改订单状态
                    DSOrder dsOrder1 = new DSOrder();
                    Long guid = order.getGuid();
                    System.out.println("修改订单状态guid" + guid);
                    dsOrder1.setGuid(guid);
                    dsOrder1.setBankType(bankType);
                    System.out.println("修改订单状态bankType" + bankType);
                    dsOrder1.setModTime(LocalDateTime.now());
                    Integer status1 = orderMapper.updateOrderStatus(dsOrder1);
                    if (status1 > 0) {
                        System.out.println("修改订单状态");
                    }
                    if (map.containsKey("sign")) {
                        if (!SignUtils.checkParam(map, "dd98f6da540f7038d0b78d393079838a")) {
                            res = "验证签名不通过";
                            respString = "验证签名不通过";
                        } else {
                            String status = map.get("status");
                            if (status != null && "0".equals(status)) {
                                String result_code = map.get("result_code");
                                if (result_code != null && "0".equals(result_code)) {

                                    respString = "success";
                                }
                            }
                        }
                    }
                }
                resp.getWriter().write(respString);
                System.out.println("respString = \"successssss\"");
            }
        } catch (Exception e) {
            log.error("操作失败，原因：", e);
            System.out.println("操作失败，原因：" + e);
        }
    }
}
