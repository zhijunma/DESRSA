package com.cn.school.dto.forms.stagesmanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Data
public class DeleteStagesViewForm extends UserContextViewForm {
    /**
     * guid
     */
    private Long guid;
    /**
     * 分期名字
     */
    private String name;
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
