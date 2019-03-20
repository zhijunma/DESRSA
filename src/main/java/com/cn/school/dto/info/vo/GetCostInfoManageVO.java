package com.cn.school.dto.info.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author:HuMin Date:2019/3/1
 * Time:15:50
 */
@Data
public class GetCostInfoManageVO {

    /**
     * 驾照类型（等级）
     */
    private String driverLevel;

    /**
     * 金额
     */
    private BigDecimal driverMoney;

    /**
     * 添加人
     */
    private String addUser;
    private LocalDateTime addTime;

    /**
     * 修改人
     */
    private String modUser;
    private LocalDateTime modTime;

}
