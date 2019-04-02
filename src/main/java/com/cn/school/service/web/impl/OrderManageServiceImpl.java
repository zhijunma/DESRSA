package com.cn.school.service.web.impl;
import com.cn.school.constant.Constant;
import com.cn.school.dto.forms.order.GetOrdersViewForm;
import com.cn.school.dto.forms.order.OrderViewForm;
import com.cn.school.dto.info.vo.GetOrderInfoVO;
import com.cn.school.entity.DSOrder;
import com.cn.school.mapper.web.OrderManageMapper;
import com.cn.school.service.web.OrderManageService;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:mzj data:2019/3/25
 * 18:00
 */
@Slf4j
@Service
public class OrderManageServiceImpl implements OrderManageService {

    @Autowired
    private OrderManageMapper orderManageMapper;


    /**
     * 查看订单
     * @param viewForm
     * @return
     */
    @Override
   public RestResponse getOrders(GetOrdersViewForm viewForm) {

        DSOrder dsOrder = new DSOrder();
        //入参 可以根据status来查询
        dsOrder.setStatus(viewForm.getStatus());
         List<DSOrder> reDeOrder = orderManageMapper.getOrders(dsOrder);
         List<GetOrderInfoVO> getOrderInfoVOS = new ArrayList<>(16);
         //缓存信息到VO中
         reDeOrder.forEach(e -> {
              GetOrderInfoVO getOrderInfoVO = new GetOrderInfoVO();
              getOrderInfoVO.setGuid(e.getGuid());
              getOrderInfoVO.setOutTradeNo(e.getOutTradeNo());
              getOrderInfoVO.setBody(e.getBody());
              getOrderInfoVO.setAttach(e.getAttach());
              getOrderInfoVO.setTotalFee(e.getTotalFee());
              getOrderInfoVO.setTimeStart(e.getTimeStart());
              getOrderInfoVO.setTimeExpire(e.getTimeExpire());
              getOrderInfoVO.setStatus(e.getStatus());
              getOrderInfoVO.setMobilePhone(e.getMobilePhone());
              getOrderInfoVO.setIdCard(e.getIdCard());
              getOrderInfoVO.setUserName(e.getUserName());
              getOrderInfoVO.setPaid(e.getPaid());
              getOrderInfoVO.setPayable(e.getPayable());

              getOrderInfoVO.setAddUser(e.getAddUser());
              getOrderInfoVO.setAddTime(e.getAddTime());
              //将缓存的信息放入list中
              getOrderInfoVOS.add(getOrderInfoVO);
         });
         //返回VOList中的说有数据
         return RestResponse.success(getOrderInfoVOS) ;

    }
    /**
     *修改订单
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse updateOrderStatus(OrderViewForm viewForm) {
        //权限判断
        roleCheck(viewForm.getCurrRole());
        //入参 订单的guidList
        List<Long> guidList = viewForm.getGuidList();
        Integer state = orderManageMapper.updateOrderStatus(guidList, viewForm.getStatus(), viewForm.getCurrId(), viewForm.getCurrName());
        if (state <= 0) {
            throw new RuntimeException("修改失败！");
        }
        return RestResponse.success("修改成功！");

        }

    /**
     * 权限判断
     */
    public void roleCheck(Integer role) {
        if (!Constant.MANAGE_ROLE.equals(role)) {
            if (!Constant.MARKETING_ROLE.equals(role)) {
                log.debug("权限不足!");
                throw new RuntimeException("权限不足！");
            }
        }
    }

}
