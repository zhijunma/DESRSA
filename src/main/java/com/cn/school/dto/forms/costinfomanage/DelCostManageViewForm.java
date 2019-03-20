package com.cn.school.dto.forms.costinfomanage;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.util.List;

/**
 * @author:HuMin Date:2019/3/20
 * Time:18:08
 */
@Data
public class DelCostManageViewForm extends UserContextViewForm {

    /**
     * 删除guidList
     */
    private List<Long> guidList;

}
