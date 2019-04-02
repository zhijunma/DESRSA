package com.cn.school.service;

import com.cn.school.dto.forms.pay.InitPayViewForm;
import com.cn.school.dto.forms.pay.queryViewForm;
import com.cn.school.entity.DSOrder;
import com.cn.school.entity.DSStudentsOrder;
import com.cn.school.mapper.wx.OrderMapper;
import com.cn.school.mapper.wx.StudentsOrderMapper;
import com.cn.school.utils.pay.*;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

@SuppressWarnings("unchecked")
@Slf4j
@Service
public class GateWayService {

    private final static String version = "2.0";
    private final static String charset = "UTF-8";
    private final static String sign_type = "MD5";
    private final static String MCH_ID = "103510093004";
    private final static String KEY = "dd98f6da540f7038d0b78d393079838a";
    //    private final static String MCH_ID="7551000001";
//    private final static String KEY="9d101c97133837e13dde2d32a5054abb";
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private StudentsOrderMapper studentsOrderMapper;


    /**
     * <一句话功能简述>
     * <功能详细描述>支付请求
     *
     * @param viewForm
     * @throws ServletException
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public Map<String, String> pay(InitPayViewForm viewForm) throws ServletException, IOException {
        log.debug("支付请求...");
        System.out.println("支付请求...");
        SortedMap<String, String> map = XmlUtils.getParameterMap();
        //商品描述
        String body = viewForm.getBody();
        map.put("service", "pay.weixin.jspay");
        map.put("version", version);
        map.put("charset", charset);
        map.put("sign_type", sign_type);
        map.put("mch_id", MCH_ID);
        map.put("is_raw", "1");
        //商户订单号
        String outTradeNo = Guid.getTradeNo();
        map.put("out_trade_no", outTradeNo);
        map.put("body", body);
        //用户openid,使用测试号时此参数置空，即不要传这个参数，使用正式商户号时才传入，参数名是sub_openid
        String subOpenid = viewForm.getSub_openid();
        map.put("sub_openid", subOpenid);
        map.put("sub_appid", "wx7a643cf968956196");
        //总金额
        Integer total_fee = viewForm.getTotal_fee();
        map.put("total_fee", total_fee.toString());
        //通知地址
        map.put("notify_url", "http://a.hmds.cn/pay/notification");
        String nonceStr = Guid.getTradeNo();
        map.put("nonce_str", nonceStr);
        map.put("attach", "附加信息");

        //IP地址
        String ip = IpUtil.getIp();
        map.put("mch_create_ip", ip);
        //map.put("mch_create_ip", "127.0.0.1");

        Map<String, String> params = SignUtils.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        SignUtils.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + KEY, "utf-8");
        map.put("sign", sign);
        String reqUrl = "https://pay.spdb.swiftpass.cn/pay/gateway";
        log.debug("reqUrl：" + reqUrl);
        log.debug("reqParams:" + XmlUtils.parseXML(map));

        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String res = null;

        Map<String, String> resultMap = null;
        String pay_info = null;
        try {
            HttpPost httpPost = new HttpPost(reqUrl);
            StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map), "utf-8");
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;utf-8");
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            if (response != null && response.getEntity() != null) {
                resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
                res = XmlUtils.toXml(resultMap);
                log.debug("请求结果：" + res);
                System.out.println("请求结果：" + res);
                if (!SignUtils.checkParam(resultMap, KEY)) {
                    //if(!sign.equals(resultMap.get("sign"))){
                    res = "验证签名不通过";
                } else {
                    if ("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))) {
                        pay_info = resultMap.get("pay_info");
                        // TODO 订单信息入库 添加add mod等信息
                        DSOrder dsOrder = new DSOrder();
                        dsOrder.setService("pay.weixin.jspay");
                        dsOrder.setVersion(version);
                        dsOrder.setCharset(charset);
                        dsOrder.setSignType(sign_type);
                        dsOrder.setMchId(MCH_ID);
                        dsOrder.setIsRaw("1");
                        dsOrder.setOutTradeNo(outTradeNo);
                        dsOrder.setDeviceInfo("");
                        dsOrder.setTotalFee(total_fee != null ? total_fee : 0);
                        dsOrder.setBody(body);
                        dsOrder.setSubOpenid(subOpenid);
                        dsOrder.setSubAppid("wx7a643cf968956196");
                        dsOrder.setMchCreateIp(ip);
                        dsOrder.setNotifyUrl("http://a.hmds.cn/pay/notification");
                        dsOrder.setNonceStr(nonceStr);
                        dsOrder.setSign(sign);
                        dsOrder.setStatus(0);
                        dsOrder.setAddTime(LocalDateTime.now());
                        dsOrder.setAddUser("user1");
                        dsOrder.setAddUserId(001L);
                        dsOrder.setModTime(LocalDateTime.now());
                        dsOrder.setModUser("user1");
                        dsOrder.setModUserId(001L);
                        Integer status = orderMapper.addOrder(dsOrder);
                        if (status > 0) {
                            log.info("添加订单信息成功！");
                            System.out.println("添加订单信息成功！");
                            //学员订单表入库 获取报名学生的GUID 和 上面生成订单的guid
                            DSStudentsOrder dsStudentsOrder = new DSStudentsOrder();
                            dsStudentsOrder.setOrderGuid(dsOrder.getGuid());
                            dsStudentsOrder.setStudentsGuid(viewForm.getStudentsGuid());
                            dsStudentsOrder.setAddTime(LocalDateTime.now());
                            dsStudentsOrder.setAddUser("user1");
                            dsStudentsOrder.setAddUserId(001L);
                            dsStudentsOrder.setModTime(LocalDateTime.now());
                            dsStudentsOrder.setModUser("user1");
                            dsStudentsOrder.setModUserId(001L);
                            studentsOrderMapper.addStudentsOrder(dsStudentsOrder);

                        } else {
                            throw new RuntimeException("添加订单信息失败！");
                        }
                        log.debug("pay_info : " + pay_info);
                        res = "ok";
                    }
                }
            } else {
                res = "操作失败";
            }
        } catch (Exception e) {
            log.error("操作失败，原因：", e);
            res = "系统异常";
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
        Map<String, String> result = new HashMap<String, String>();
        if ("ok".equals(res)) {
            result.put("status", "200");
            result.put("pay_info", pay_info);

        } else {
            result.put("status", "500");
            result.put("msg", res);
        }
        return result;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>订单查询
     *
     * @param viewForm
     * @throws ServletException
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> query(queryViewForm viewForm) throws ServletException, IOException {
        log.debug("订单查询...");
        SortedMap<String, String> map = XmlUtils.getParameterMap();

        map.put("service", "pay.weixin.jspay");
        map.put("version", version);
        map.put("charset", charset);
        map.put("sign_type", sign_type);
        map.put("mch_id", MCH_ID);
        map.put("out_trade_no", viewForm.getOutTradeNo());
//        String key = SwiftpassConfig.key;
        String reqUrl = "https://pay.swiftpass.cn/pay/gateway";
        map.put("nonce_str", viewForm.getNonceStr());

        Map<String, String> params = SignUtils.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        SignUtils.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + KEY, "utf-8");
        map.put("sign", sign);

//        log.debug("reqUrl:" + reqUrl);

        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String res = null;
        Map<String, String> resultMap = new HashMap<>();
        try {
            HttpPost httpPost = new HttpPost(reqUrl);
            StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map), "utf-8");
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;utf-8");
            client = HttpClients.createDefault();
            response = client.execute(httpPost);

            if (response != null && response.getEntity() != null) {
                resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
                res = XmlUtils.toXml(resultMap);
                log.debug("请求结果：" + res);

                if (!SignUtils.checkParam(resultMap, KEY)) {
                    res = "验证签名不通过";
                } else {
                    if ("0".equals(resultMap.get("status"))) {
                        if ("0".equals(resultMap.get("result_code"))) {
                            log.debug("业务成功，在这里做相应的逻辑处理");
                            String trade_state = resultMap.get("trade_state");
                            log.debug("trade_state : " + trade_state);
                            log.debug("这里商户需要同步自己的订单状态。。。");
                        } else {
                            log.debug("业务失败，尝试重新请求，并查看错误代码描叙");
                        }
                    } else {
                        log.debug("这里是请求参数有问题...");
                    }
                }
            } else {
                res = "操作失败!";
            }
        } catch (Exception e) {
            log.error("操作失败，原因：", e);
            res = "操作失败";
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
        Map<String, Object> result = new HashMap<>();
        if (res.startsWith("<")) {
            result.put("status", "200");
            result.put("msg", "操作成功，请在日志文件中查看");
            result.put("resultMap", resultMap);
        } else {
            result.put("status", "500");
            result.put("msg", res);
        }
        return result;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>退款查询
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public void refundQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("退款查询...");
        SortedMap<String, String> map = XmlUtils.getParameterMap();

        map.put("service", "unified.trade.refundquery");
        map.put("version", version);
        map.put("charset", charset);
        map.put("sign_type", sign_type);
        map.put("out_trade_no", "162719924935378");
        String key = "9d101c97133837e13dde2d32a5054abb";
        String reqUrl = "https://pay.spdb.swiftpass.cn/pay/gateway";
        map.put("mch_id", "7551000001");
        map.put("nonce_str", String.valueOf(LocalDateTime.now()));

        Map<String, String> params = SignUtils.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        SignUtils.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + key, "utf-8");
        map.put("sign", sign);

        log.debug("reqUrl:" + reqUrl);

        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String res = null;
        try {
            HttpPost httpPost = new HttpPost(reqUrl);
            StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map), "utf-8");
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;utf-8");
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            if (response != null && response.getEntity() != null) {
                Map<String, String> resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
                res = XmlUtils.toXml(resultMap);
                log.debug("请求结果：" + res);

                if (!SignUtils.checkParam(resultMap, key)) {
                    res = "验证签名不通过";
                }
            } else {
                res = "操作失败!";
            }
        } catch (Exception e) {
            log.error("操作失败，原因：", e);
            res = "操作失败";
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
        Map<String, String> result = new HashMap<String, String>();
        if (res.startsWith("<")) {
            result.put("status", "200");
            result.put("msg", "操作成功，请在日志文件中查看");
        } else {
            result.put("status", "500");
            result.put("msg", res);
        }
        resp.getWriter().write(new Gson().toJson(result));
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>退款
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public void refund(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("退款...");
        SortedMap<String, String> map = XmlUtils.getParameterMap();

        map.put("service", "unified.trade.refund");
        map.put("version", version);
        map.put("charset", charset);
        map.put("sign_type", sign_type);
        map.put("total_fee", "1000");
        map.put("refund_fee", "1000");
        map.put("out_refund_no", "1512318564311");
        String key = "9d101c97133837e13dde2d32a5054abb";
        String reqUrl = "https://pay.spdb.swiftpass.cn/pay/gateway";
        map.put("mch_id", "7551000001");
        map.put("op_user_id", "7551000001");
        map.put("nonce_str", "1553238268281");
        map.put("out_trade_no", "162719924935378");

        Map<String, String> params = SignUtils.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        SignUtils.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + key, "utf-8");
        map.put("sign", sign);

        log.debug("reqUrl:" + reqUrl);

        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String res = null;
        try {
            HttpPost httpPost = new HttpPost(reqUrl);
            StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map), "utf-8");
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;utf-8");
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            if (response != null && response.getEntity() != null) {
                Map<String, String> resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
                res = XmlUtils.toXml(resultMap);
                log.debug("请求结果：" + res);

                if (!SignUtils.checkParam(resultMap, key)) {
                    res = "验证签名不通过";
                }
            } else {
                res = "操作失败!";
            }
        } catch (Exception e) {
            log.error("操作失败，原因：", e);
            res = "操作失败";
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
        Map<String, String> result = new HashMap<String, String>();
        if (res.startsWith("<")) {
            result.put("status", "200");
            result.put("msg", "操作成功，请在日志文件中查看");
        } else {
            result.put("status", "500");
            result.put("msg", res);
        }
        resp.getWriter().write(new Gson().toJson(result));
    }
}
