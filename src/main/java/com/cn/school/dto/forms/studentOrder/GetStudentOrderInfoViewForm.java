package com.cn.school.dto.forms.studentOrder;

import lombok.Data;

/**
 * @Author: leiyunlong
 * @Date: 2019/4/1 16:44
 * @Version 1.0
 */
@Data
public class GetStudentOrderInfoViewForm {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 身份证号
     */
    private String idCard;
}
