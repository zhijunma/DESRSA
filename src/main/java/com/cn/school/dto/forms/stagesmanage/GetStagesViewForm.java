package com.cn.school.dto.forms.stagesmanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author:HuMin Date:2019/3/18
 * Time:19:49
 */
@Data
public class GetStagesViewForm extends UserContextViewForm {

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