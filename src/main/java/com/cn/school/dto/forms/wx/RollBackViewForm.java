package com.cn.school.dto.forms.wx;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: leiyunlong
 * @Date: 2019/3/27 9:54
 * @Version 1.0
 */
@Data
@Component
public class RollBackViewForm {
    private String code;
    private String state;
}
