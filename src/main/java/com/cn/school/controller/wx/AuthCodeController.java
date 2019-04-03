package com.cn.school.controller.wx;

import com.alibaba.fastjson.JSONObject;
import com.cn.school.dto.forms.wx.RollBackViewForm;
import com.cn.school.service.wx.AuthCodeService;
import com.cn.school.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: leiyunlong
 * @Date: 2019/3/26 18:51
 * @Version 1.0
 */
@RestController
@Api(description = "PayController", tags = {"获取openId"})
public class AuthCodeController {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AuthCodeService authCodeService;

    /**
     * 获取openId
     *
     * @param rollBackViewForm
     * @return
     */
    @PostMapping("rollback")
    @ApiOperation(value = "获取openId，返回值没有封装")
    public Object rollback(@RequestBody RollBackViewForm rollBackViewForm) {
        //TODO 返回值没有封装
        if (redisUtil.get(rollBackViewForm.getCode()) != null) {
            return redisUtil.get(rollBackViewForm.getCode());
        }
        String data = authCodeService.rollback(rollBackViewForm.getCode());
        /**
         * header和cookie保持一致
         */
        String header_string = "refresh_token";
        JSONObject jsonObject = JSONObject.parseObject(data);
        redisUtil.set(rollBackViewForm.getCode(), jsonObject);
        return jsonObject;
    }
}
