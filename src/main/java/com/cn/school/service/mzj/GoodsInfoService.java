package com.cn.school.service.mzj;

import com.cn.school.FormView.GetGoodsViewForm;
import com.cn.school.entity.mzj.DSGoodsInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional
public interface GoodsInfoService {
    /**
     *
     * @param form
     * @return
     */
    List<DSGoodsInfo> getGoodsInfo (GetGoodsViewForm form);
}
