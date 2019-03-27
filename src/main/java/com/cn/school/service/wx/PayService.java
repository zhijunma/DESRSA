package com.cn.school.service.wx;

import com.cn.school.dto.forms.pay.InitPayViewForm;
import com.cn.school.utils.response.RestResponse;

public interface PayService {
    RestResponse initPay(InitPayViewForm initViewForm);
}
