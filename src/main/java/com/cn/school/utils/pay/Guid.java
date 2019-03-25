package com.cn.school.utils.pay;

import java.util.UUID;

/**
 * @Author: leiyunlong
 * @Date: 2019/3/24 16:26
 * @Version 1.0
 */
public class Guid {
    /**
     * 生成32伪随机字符串
     *
     * @return
     */
    public static String getTradeNo() {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }
}
