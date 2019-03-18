package com.cn.school.aop;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.school.config.MyHttpServletRequestWrapper;
import com.cn.school.utils.RedisUtil;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author:HuMin Date:2019/3/15
 * Time:15:10
 */
@Slf4j
public class ApiParameterInterceptor implements HandlerInterceptor {
    @Value("${redisActiveTime}")
    private String redisActiveTime;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 在controller处理之前首先对请求验证参数验签处理。
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("---------------拦截器参数验签------------------");
        JSONObject responseJson = new JSONObject();
        try {
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            //请求方法
            String requestMethord = request.getRequestURI();
            if (requestMethord == null) {
                return false;
            }

            // 获取Content-Type=application/json 请求参数
            JSONObject parameterMap = JSON.parseObject(new MyHttpServletRequestWrapper(request).getBodyString(request));
            if (parameterMap.isEmpty()) {
                return false;
            }
            Map<String,Object> header = (Map<String,Object>)parameterMap.get("header");
            if (header == null) {
                return false;
            }
            String token = header.get("token").toString();
            if (token.isEmpty()) {
                return false;
            }
            if (redisUtil.hasKey(token)) {
                redisUtil.expire(token, Long.valueOf(redisActiveTime).longValue());
            }

        } catch (Exception e) {
            log.error("接口调用异常：", e);
            e.printStackTrace();
            RestResponse.error("接口调用异常");
            ajaxResponseJsonReturn(response, request, responseJson);
            return false;
        }
        log.info("验签通过。");
        return true;
    }

    protected void ajaxResponseJsonReturn(HttpServletResponse response, HttpServletRequest request, JSONObject responseJson) throws IOException {

        //Constant.ENCODE_UTF8
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(responseJson.toJSONString());
        out.flush();
        out.close();
    }


}
