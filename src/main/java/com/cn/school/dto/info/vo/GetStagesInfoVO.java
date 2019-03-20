package com.cn.school.dto.info.vo;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetStagesInfoVO extends UserContextViewForm {
    /**
     * guid
     */
    private Long guid;
    /**
     * 分期活动名称
     */
    private String name;

    /**
     * 分期活动期次
     */
    private Integer issues;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 添加人
     */
    private Long addUserId;
    private LocalDateTime addTime;
    private String addUser;
    /**
     * 修改人
     */
    private LocalDateTime modTime;
    private String modUser;
    private Long modUserId;
}
