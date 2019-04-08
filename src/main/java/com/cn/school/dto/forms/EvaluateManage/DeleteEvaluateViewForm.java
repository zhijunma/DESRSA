package com.cn.school.dto.forms.EvaluateManage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class DeleteEvaluateViewForm extends UserContextViewForm {

        /**
         * guid
         */
        private List<Long> guidList;

}

