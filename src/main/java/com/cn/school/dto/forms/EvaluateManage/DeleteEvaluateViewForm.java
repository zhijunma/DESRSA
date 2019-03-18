package com.cn.school.dto.forms.EvaluateManage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class DeleteEvaluateViewForm extends UserContextViewForm {


        /**
         * 添加人
         */
        private Long addUserId;
        private String addUser;


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

