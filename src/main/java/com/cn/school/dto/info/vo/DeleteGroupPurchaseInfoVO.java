package com.cn.school.dto.info.vo;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeleteGroupPurchaseInfoVO extends UserContextViewForm {
    /**
     * 主键id
     */
    private Long guid;
    /**
     * 修改人
     */
    private Long modUserId;
    private String modUser;
    private LocalDateTime modTime;

}
