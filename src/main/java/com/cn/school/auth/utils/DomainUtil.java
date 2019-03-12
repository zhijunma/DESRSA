
package com.cn.school.auth.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 域名工具
 *
 * @since 2019-01-09
 * @author shiding
 */
@Slf4j
public class DomainUtil {
    /**
     * 匹配模式
     */
    private Pattern pattern;
    /**
     * 本地域名
     */
    private static final String LOCALHOST = "localhost";

    /**
     * 定义正则表达式
     */
    private static final String RE_TOP = "[\\w-]+\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co)\\b()*";

    /**
     * 构造
     *
     */
    public DomainUtil() {
        pattern = Pattern.compile(RE_TOP, Pattern.CASE_INSENSITIVE);
    }

    /**
     * 获取顶级域名
     * 
     * @param url
     * @return String
     */
    public String getTopDomain(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        String result = url.toLowerCase();
        try {
            if (url.toLowerCase().indexOf(LOCALHOST) > -1) {
                URL currUrl = new URL(result);
                result = currUrl.getHost();
            } else {
                Matcher matcher = this.pattern.matcher(url.toLowerCase());
                matcher.find();
                result = matcher.group();
            }
        } catch (Exception e) {
            log.error("Get top domain error.", e);
        }
        return result;
    }
}
