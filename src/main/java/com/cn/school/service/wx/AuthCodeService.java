package com.cn.school.service.wx;

/**
 * 获取openId
 *
 * @Author: leiyunlong
 * @Date: 2019/3/26 19:05
 * @Version 1.0
 */
public interface AuthCodeService {
    /**
     * 获取openId
     *
     * @param code
     * @return
     */
    String rollback(String code);
}
