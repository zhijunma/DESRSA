package com.cn.school.dto.forms.costinfomanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author:HuMin Date:2019/3/20
 * Time:18:05
 */
@Data
public class AddCostManageViewForm extends UserContextViewForm {

    /**
     * 驾照类型（等级）
     */
    private String driverLevel;

    /**
     * 金额
     */
    private BigDecimal driverMoney;
}
