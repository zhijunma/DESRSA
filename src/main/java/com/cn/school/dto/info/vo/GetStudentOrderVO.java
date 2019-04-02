package com.cn.school.dto.info.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: leiyunlong
 * @Date: 2019/4/1 16:54
 * @Version 1.0
 */
@Data
public class GetStudentOrderVO {
    /**
     * 订单（表)时间
     */
    private LocalDateTime dso_add_time;
    /**
     * 金额
     */
    private Integer totalFee;
    /**
     * 订单状态  0：未交费 1：已缴费
     */
    private int status;
}
