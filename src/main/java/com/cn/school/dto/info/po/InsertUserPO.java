package com.cn.school.dto.info.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author:HuMin Date:2019/3/1
 * Time:16:57
 */
@Data
public class InsertUserPO {
    /**
     * 主键id
     */
    private Long guid;

    /**
     * 用户名
     */
    private String userName;

//    /**
//     * 用户密码
//     */
//    private String password;
//
//    /**
//     * 用户密码
//     */
//    private String salt;

    /**
     * 电话号码
     */
    private String mobilePhone;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 缴费状态  0：未交费 1 ：以缴费
     */
    private Integer status;

    /**
     * 角色   1：学员   2：教练
     */
    private Integer role;
    /**
     * 分期id
     */
    private Long stagesGuid;
    /**
     * 添加人
     */
    private Long addUserId;
    private String addUser;
    private LocalDateTime addTime;

    /**
     * 修改人
     */
    private Long modUserId;
    private String modUser;
    private LocalDateTime modTime;

    /**
     * 0：未删除 1：删除
     */
    private Boolean deleteFlag;


}
