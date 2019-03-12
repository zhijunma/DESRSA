
package com.cn.school.global;

/**
 * 缓存操作常量定义
 *
 * @since 2018-01-09
 * @author shiding
 */
public interface CacheConstants {
    /**
     * 用户上下文缓存名称
     */
    String SUPPLIER_USER_CONTEXT_CACHE_NAME = "ERP_SUPPLIER_USER_CONTEXT";
    /**
     * 用户上下文手机缓存名称
     */
    String SUPPLIER_USER_TEL_CONTEXT_CACHE_NAME = "ERP_SUPPLIER_USER_TEL_CONTEXT";
    /**
     * 存储超期时长（默认24小时）
     */
    long SER_CONTEXT_EXPIRE_SECOND = 3600 * 24 * 7;
}
