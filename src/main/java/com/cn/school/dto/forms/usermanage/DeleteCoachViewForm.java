package com.cn.school.dto.forms.usermanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeleteCoachViewForm extends UserContextViewForm {
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 修改人信息
     */
    private Long modUserId;
    private String modUser;
    private LocalDateTime modTime;
}

