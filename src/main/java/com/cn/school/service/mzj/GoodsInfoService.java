package com.cn.school.service.mzj;

import com.cn.school.FormView.GetGoodsViewForm;
import com.cn.school.entity.mzj.DSGoodsInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author Administrator
 */
@Transactional
public interface GoodsInfoService {
    /**
     *获取商品列表
     * @param form
     * @return
     */
    List<DSGoodsInfo> getGoodsInfo (GetGoodsViewForm form);
}
