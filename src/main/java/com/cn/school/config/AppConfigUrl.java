package com.cn.school.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * app配置
 */

@Component
@ConfigurationProperties(prefix = "app.config")
@Data
public class  AppConfigUrl{
    /**
     * 有效时间
     * @默认 默认时间一个小时
     */
    private long EXPIRATION_TIME = 3600_000; // 1 hour
    /**
     * 加密秘钥
     */
    private String SECRET = "ThisIsASecret";
    /**
     * @默认 加密前缀
     */
    private String TOKEN_PREFIX = "Bearer";//加密前缀
    /**
     * header头验证参数
     */
    private String HEADER_STRING = "Authorization";
    /**
     * url携带参数
     */
    private String ACCESS_TOKEN = "access_token";
    /**
     * 开放路径
     */
    private String[] permits=new String[]{"*/*"};

}