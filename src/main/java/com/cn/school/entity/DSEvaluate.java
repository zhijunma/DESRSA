package com.cn.school.entity;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class DSEvaluate{
        /**
         * 主键id
         */
        private Long guid;

        /**
         * 评价内容
         */
        private String comments;

        /**
         * 分数
         */
        private Integer score;

        /**
         * 是否展示 0 不展示，1 展示
         */
        private Integer showflag;

        /**
         * 添加人
         */
        private Long addUserId;
        private String addUser;
        private LocalDateTime addTime;

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
