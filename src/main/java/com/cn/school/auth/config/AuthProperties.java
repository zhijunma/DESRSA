
package com.cn.school.auth.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Properties;

/**
 * 鉴权配置
 */
@Component
@Validated
@ConfigurationProperties(prefix = "supplier.auth")
@NoArgsConstructor
@Data
public class AuthProperties {
    /**
     * 供应商授权Feign名称
     */
    private String feignName = "Driver_School";
    /**
     * 供应商鉴权黑名单，使用','号分隔多个值
     */
    private String[] blackList = new String[]{};
    /**
     * 供应商URI前缀
     */
    private String uriPrefix = "";

    private boolean accessEnable = false;
    /**
     * 是否使用Session方式，
     * 使用Session则不支持跳转重定向和跨域，默认使用Cookie
     */
    private boolean sessionEnable = false;
    /**
     * 是否使用顶级域名存储Cookie，仅当使用Cookie时生效
     */
    private boolean topdomainEnable = true;

    /**
     * 用于多配置项的属性信息
     */
    private Properties config;

}
