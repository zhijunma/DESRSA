package com.cn.school.auth.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * cookie工具类
 *
 * @author shiding
 */
public class CookieUtil {

    /**
     * 根据key获取cookie
     *
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>(1000);
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 保存Cookies
     *
     * @param response
     * @param name
     * @param value
     * @param time
     * @return
     */
    public static HttpServletResponse setCookie(HttpServletResponse response, String name, String value, int time) {
        return setCookie(response, null, name, value, time);
    }

    /**
     * 保存Cookies
     *
     * @param response
     * @param domain
     * @param name
     * @param value
     * @param time
     * @return
     */
    public static HttpServletResponse setCookie(HttpServletResponse response, String domain, String name, String value,
                                                int time) {
        // new一个Cookie对象,键值对为参数
        Cookie cookie = new Cookie(name, value);
        // 设置域名
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        // tomcat下多应用共享
        cookie.setPath("/");
        // 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
        try {
            URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cookie.setMaxAge(time);
        // 将Cookie添加到Response中,使之生效
        // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
        response.addCookie(cookie);
        return response;
    }

    /**
     * 根据cookie key删除cookie
     *
     * @param request
     * @param response
     * @param deleteKey
     * @throws NullPointerException
     */
    public static void delectCookieByName(HttpServletRequest request, HttpServletResponse response, String deleteKey)
            throws NullPointerException {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        for (String key : cookieMap.keySet()) {
            if (key == deleteKey && key.equals(deleteKey)) {
                Cookie cookie = cookieMap.get(key);
                // 设置cookie有效时间为0
                cookie.setMaxAge(0);
                // 不设置存储路径
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }

    /**
     * @param response
     * @param cookie
     */
    public static void deleteCookie(HttpServletResponse response, Cookie cookie) {
        // 设置cookie有效时间为0
        cookie.setMaxAge(0);
        // 不设置存储路径
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}