package com.cn.school.dto.forms.EvaluateManage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddEvaluateViewForm extends UserContextViewForm {

    /**
     * 评价内容
     */
    private String comments;

    /**
     * 分数
     */
    private Integer score;

}
