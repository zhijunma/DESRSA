package com.cn.school.service;

import com.cn.school.config.SwiftpassConfig;
import com.cn.school.utils.pay.MD5;
import com.cn.school.utils.pay.SignUtils;
import com.cn.school.utils.pay.XmlUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

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
public class GateWayService {

    private final static String version = "2.0";
    private final static String charset = "UTF-8";
    private final static String sign_type = "MD5";

    /**
     * <一句话功能简述>
     * <功能详细描述>支付请求
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public void pay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("支付请求...");
        SortedMap<String, String> map = XmlUtils.getParameterMap(req);

        map.put("service", "pay.weixin.jspay");
        map.put("version", version);
        map.put("charset", charset);
        map.put("sign_type", sign_type);
        map.put("is_raw", "1");
//        map.put("sub_openid", "1");
//        map.put("sub_appid", "wx7a643cf968956196");
        map.put("mch_id", "7551000001");
        map.put("notify_url", "www.baidu.com");
        map.put("nonce_str", String.valueOf(LocalDateTime.now()));
        map.put("attach", "附加信息");
        map.put("body", "测试付款");
        map.put("mch_create_ip", "127.0.0.1");
        map.put("out_trade_no", "162719924935378");
        map.put("total_fee", "1");

        Map<String, String> params = SignUtils.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        SignUtils.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + "9d101c97133837e13dde2d32a5054abb", "utf-8");

        map.put("sign", sign);

        String reqUrl = "https://pay.swiftpass.cn/pay/gateway";
        log.debug("reqUrl：" + reqUrl);

        log.debug("reqParams:" + XmlUtils.parseXML(map));

        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String res = null;

        Map<String, String> resultMap = null;
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

                if (!SignUtils.checkParam(resultMap,
                        "9d101c97133837e13dde2d32a5054abb")) {
                    res = "验证签名不通过";
                } else {
                    if ("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))) {
                        String pay_info = resultMap.get("pay_info");
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
            result = resultMap;
        } else {
            result.put("status", "500");
            result.put("msg", res);
        }
        resp.getWriter().write(new Gson().toJson(resultMap));
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>订单查询
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("订单查询...");
        SortedMap<String, String> map = XmlUtils.getParameterMap(req);

        map.put("service", "unified.trade.query");
        map.put("version", version);
        map.put("charset", charset);
        map.put("sign_type", sign_type);
        map.put("mch_id", SwiftpassConfig.mch_id);

        String key = SwiftpassConfig.key;
        String reqUrl = SwiftpassConfig.req_url;
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
        SortedMap<String, String> map = XmlUtils.getParameterMap(req);

        map.put("service", "unified.trade.refundquery");
        map.put("version", version);
        map.put("charset", charset);
        map.put("sign_type", sign_type);

        String key = SwiftpassConfig.key;
        String reqUrl = SwiftpassConfig.req_url;
        map.put("mch_id", SwiftpassConfig.mch_id);
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
        SortedMap<String, String> map = XmlUtils.getParameterMap(req);

        map.put("service", "unified.trade.refund");
        map.put("version", version);
        map.put("charset", charset);
        map.put("sign_type", sign_type);

        String key = SwiftpassConfig.key;
        String reqUrl = SwiftpassConfig.req_url;
        map.put("mch_id", SwiftpassConfig.mch_id);
        map.put("op_user_id", SwiftpassConfig.mch_id);
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
}
