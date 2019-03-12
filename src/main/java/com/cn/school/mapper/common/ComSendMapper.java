package com.cn.school.mapper.common;

import com.cn.school.entity.DSCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author:HuMin Date:2019/3/4
 * Time:15:19
 */
@Mapper
public interface ComSendMapper {
    /**
     * 新增验证码
     *
     * @param dsCode
     * @return
     */
    Integer insertMobileCode(
            @Param("entity") DSCode dsCode);

    /**
     * 删除失效的验证码
     *
     * @param dsCode
     * @return
     */
    Integer removeMobileCode(
            @Param("entity") DSCode dsCode);

    /**
     * 校验验证码
     *
     * @param dsCode
     * @return
     */
    Integer updateMobileCode(
            @Param("entity") DSCode dsCode);
}
