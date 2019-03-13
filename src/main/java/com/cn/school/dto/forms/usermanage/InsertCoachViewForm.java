package com.cn.school.dto.forms.usermanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InsertCoachViewForm extends UserContextViewForm {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 电话号码
     */
    private String mobilePhone;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 角色   1：学员   2：教练
     */
    private Integer role;
    /**
     * 缴费状态  0：未交费 1 ：以缴费
     */
    private Integer status;
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
