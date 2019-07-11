package com.cn.school.service.mzj;

import com.cn.school.FormView.AddUserOrderViewForm;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单管理
 * @author Administrator
 */
@Transactional
public interface OrdersManageService {
    /**
     * 添加用户订单
     * @param form
     * @return
     */
    String addUserOrder(AddUserOrderViewForm form);


}
