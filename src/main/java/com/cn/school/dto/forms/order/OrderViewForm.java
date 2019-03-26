package com.cn.school.dto.forms.order;

import com.cn.school.dto.forms.auth.UserContextViewForm;
import lombok.Data;

import java.util.List;

/**
 * @author:mzj Date:2019/3/26
 * Time:16:40
 */
@Data
public class OrderViewForm extends UserContextViewForm {
    /**
     * guid
     */
    private List<Long> guidList;
    /**
     * 订单状态
     */
    private Integer status;
}
