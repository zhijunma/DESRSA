package com.cn.school.dto.forms.usermanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class DeleteStudnetViewForm extends UserContextViewForm {
    /**
     * 主键id
     */
    private List<Long> guidList;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 身份证号
     */
    private String idCard;


    /**
     * 修改人
     */
    private Long modUserId;
    private String modUser;
    private LocalDateTime modTime;
}
