package com.cn.school.dto.info.vo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class GetEvaluateInfoVO {

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
