package com.cn.school.dto.info.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author:HuMin Date:2019/3/12
 * Time:14:49
 */
@Data
public class LoginUserPO {

    private Long guid;
    /**
     * 电话号码
     */
    private String mobilePhone;
    /**
     * 用户密码
     */
    private String password;


    /**
     * 盐
     */
    private String salt;
    /**
     * 修改人
     */
    private Long modUserId;
    private String modUser;
    private LocalDateTime modTime;
}
