package com.cn.school.service.common.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.cn.school.constant.SmsConstant;
import com.cn.school.dto.forms.ComSendCodeViewForm;
import com.cn.school.dto.info.bo.GetSystemCodeMessageBO;
import com.cn.school.entity.DSCode;
import com.cn.school.mapper.common.ComSendMapper;
import com.cn.school.service.common.SmsService;
import com.cn.school.constant.CodeMsg;
import com.cn.school.utils.SmsUtil;
import com.cn.school.utils.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author:HuMin Date:2019/3/4
 * Time:12:02
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    /**
     * 短信模板
     */
    String codeType = "SMS_159626833";

    /**
     * 发送短信Mapper
     */
    @Autowired
    private ComSendMapper comSendMapper;

    /**
     * 发送短信
     *
     * @param viewForm
     * @return
     */
    @Override
    public RestResponse sendCode(ComSendCodeViewForm viewForm) {
        // 手机号
        String mobile = viewForm.getMobile();
        //发送短信验证码
        return SendCode(mobile);
    }

    /**
     * 发送短信验证码
     *
     * @param mobile
     * @return
     */
    public RestResponse SendCode(String mobile) {
        // 验证码生成
        String code = generateCode();

        // 发送验证码消息BO
        GetSystemCodeMessageBO getSystemCodeMessageBO = new GetSystemCodeMessageBO();

        // 参数设定
        getSystemCodeMessageBO.setCode(code);
        getSystemCodeMessageBO.setTempCode(codeType);
        getSystemCodeMessageBO.setMobile(mobile);

        // 发送系统验证码消息
        SmsUtil smsUtil = new SmsUtil();
        SendSmsResponse res = new SendSmsResponse();
        try {
            res = smsUtil.sendSms(getSystemCodeMessageBO);
        } catch (ClientException e) {
            log.info("短信发送异常+++++++++++++++++++++++++" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        // 验证码发送成功
        if (res != null && ("OK").equals(res.getCode())) {

            // 删除失效验证码
            DSCode dsCode = new DSCode();
            dsCode.setMobilePhone(mobile);
            dsCode.setDeleteFlag(true);
            dsCode.setCodeType(codeType);
            dsCode.setModUser(mobile);
            dsCode.setModUserId(0L);
            comSendMapper.removeMobileCode(dsCode);

            // 插入验证码
            dsCode.setCode(code);
            dsCode.setAddUser(mobile);
            dsCode.setAddUserId(0L);
            dsCode.setModUser(mobile);
            dsCode.setModUserId(0L);
            dsCode.setDeleteFlag(false);
            dsCode.setStatus(0);
            int count = comSendMapper.insertMobileCode(dsCode);
            if (1 > count) {
                throw new RuntimeException("插入验证码失败！");
            }
        }else{
            throw new RuntimeException("发送验证码失败！");
        }

        return RestResponse.success(code);

    }

    /**
     * 短信验证码验证
     *
     * @param viewForm
     * @return
     */
    public RestResponse updateCheckMobileCode(ComSendCodeViewForm viewForm) {

        // 校验验证码
        DSCode dsCode = new DSCode();
        dsCode.setMobilePhone(viewForm.getMobile());
        dsCode.setCodeType(codeType);
        dsCode.setCode(viewForm.getCode());
        dsCode.setStatus(1);
        dsCode.setModUserId(0L);
        dsCode.setModUser(viewForm.getMobile());
        int count = comSendMapper.updateMobileCode(dsCode);

        if (1 > count) {
            return RestResponse.error(CodeMsg.SERVER_EXCEPTION);
        }

        return RestResponse.success();
    }

    /**
     * 生成6位验证码
     *
     * @return String
     */
    private static String generateCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SmsConstant.AUTH_CODE_LENGTH; i++) {
            int index = new Random().nextInt(SmsConstant.AUTH_CODE.length());
            sb.append(SmsConstant.AUTH_CODE.charAt(index));
        }
        return sb.toString();
    }
}
