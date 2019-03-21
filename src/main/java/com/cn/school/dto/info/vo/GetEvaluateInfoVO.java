package com.cn.school.dto.info.vo;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class GetEvaluateInfoVO extends UserContextViewForm {
    /**
     * guid
     */
    private Long guid;
    /**
     * 评价内容
     */
    private String data;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 添加人
     */

    private String addUser;
    private LocalDateTime addTime;

}
