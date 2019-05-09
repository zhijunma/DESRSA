package com.cn.school.controller.mzj;

import com.cn.school.FormView.AddRSAViewForm;
import com.cn.school.FormView.GetRSAViewForm;
import com.cn.school.service.mzj.CustomRSAService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mzj Date:4.22
 * Time 21:13
 */
@RestController
@Api(description = "CustomRSAController",tags = {"RSA加密算法controller"})
@ApiModel(value="RSA加密算法",description="RSA加密算法")
@RequestMapping("/customRSA")
public class CustomRSAController {
    @Autowired
    CustomRSAService customRSAService;

    /**
     * 加密
     * @param addRSAViewForm
     * @return
     */
    @ApiOperation(value = "RSA加密")
    @PostMapping(value = "/addRSA")
    public String addRSA(AddRSAViewForm addRSAViewForm) {
        String word = addRSAViewForm.getWord();
        Integer keySize = addRSAViewForm.getKeySize();
        return customRSAService.addRSA(word,keySize);
    }
    /**
     * 解密
     * @param form
     * @return
     */
    @ApiOperation(value = "RSA解密")
    @PostMapping(value = "/getRSA")
    public String getRSA(GetRSAViewForm form) {
        return customRSAService.getRSA(form);
    }
    /**
     *
     * @param form
     * @return
     */
    @ApiOperation(value = "获取密文")
    @PostMapping(value = "/getRSAPasswordById")
    public String getRSAPasswordById(GetRSAViewForm form) {
        return customRSAService.getRSAPasswordById(form);
    }
    /**
     *
     * @param form
     * @return
     */
    @ApiOperation(value = "获取公钥")
    @PostMapping(value = "/getRSAPublicKeyById")
    public String getRSAPublicKeyById(GetRSAViewForm form) {
        return customRSAService.getRSAPublicKeyById(form);
    }
    /**
     *
     * @param form
     * @return
     */
    @PostMapping(value = "/getRSAPrivateKeyById")
    @ApiOperation(value = "获取私钥")
    public String getRSAPrivateKeyById(GetRSAViewForm form) {
        return customRSAService.getRSAPrivateKeyById(form);
    }
}
