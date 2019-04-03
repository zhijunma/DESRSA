package com.cn.school.dto.forms.usermanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DeleteCoachViewForm extends UserContextViewForm {
    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private List<String> idCardList;
    /**
     * 修改人信息
     */
    private Long modUserId;
    private String modUser;
    private LocalDateTime modTime;
}

