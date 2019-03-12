
package com.cn.school.auth.utils;

import com.cn.school.dto.info.UserContextInfo;

/**
 * 用户上下文工具
 *
 * @author shiding
 * @since 2019-01-09
 */
public class UserContextHolder {
    /**
     * 用户上下文
     */
    private static ThreadLocal<UserContextInfo> currentSupplierUserContext = new ThreadLocal<>();

    /**
     * 设置用户上下文
     *
     * @param supplierUserContext
     */
    public static void set(UserContextInfo supplierUserContext) {
        currentSupplierUserContext.set(supplierUserContext);
    }

    /**
     * 获取用户上下文
     *
     * @return supplierUserContext
     */
    public static UserContextInfo get() {
        return currentSupplierUserContext.get();
    }

    /**
     * 删除用户上下文
     */
    public static void remove() {
        currentSupplierUserContext.remove();
    }

}
