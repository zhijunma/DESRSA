package com.cn.school.controller.mzj;

import com.cn.school.FormView.GetGoodsViewForm;
import com.cn.school.FormView.GetUserInfoViewForm;
import com.cn.school.entity.mzj.DSGoodsInfo;
import com.cn.school.service.mzj.GoodsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "GoodsInfoController",tags = {"商品信息条件获取"})
@ApiModel(value="商品信息",description="商品信息")
@RequestMapping(value = "/GoodsInfo")
public class GoodsInfoController {
    @Autowired
    GoodsInfoService goodsInfoService;

    @PostMapping(value = "/getGoodsInfo")
    @ApiOperation(value="查找商品")
    @ResponseBody
    public List<DSGoodsInfo> getGoodsInfo(GetGoodsViewForm form){
        return goodsInfoService.getGoodsInfo(form);
    }
}
