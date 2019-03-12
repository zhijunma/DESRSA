
package com.cn.school.auth.global;

/**
 * Cookie常量定义
 *
 * @since 2018-01-09
 * @author shiding
 */
public interface CookieConstants {
    /**
     * 供应商授权令牌Cookie名
     */
    String SUPPLIER_TOKEN_NAME = "ERP_SUPPLIER_TOKEN";
    /**
     * 默认供应商授权令牌超期时长秒数
     */
    int SUPPLIER_TOKEN_COOKIE_EXPIRED = 7200;
}
