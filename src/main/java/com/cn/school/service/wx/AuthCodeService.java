package com.cn.school.service.wx;

/**
 * @Author: leiyunlong
 * @Date: 2019/3/26 19:05
 * @Version 1.0
 */
public interface AuthCodeService {
    String rollback(String code, String state);
}
