package com.cn.school.controller.mzj;

import com.cn.school.FormView.AddDESViewForm;
import com.cn.school.FormView.GetDESViewForm;
import com.cn.school.FormView.GetWordViewForm;
import com.cn.school.service.mzj.CustomDESService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author mzj
 * @date 2019-3-28 上午10:12:11
 */
@RestController
@Api(value="",description="CustomDESController",tags={"DES算法controller"})
@ApiModel(value="DES加密算法",description="DES加密算法")
@RequestMapping("/web/customDES")
public class CustomDESController {
    @Autowired
    CustomDESService customDESService;
    /**
     * 加密
     * @param addDESViewForm
     * @return
     */
    @PostMapping(value = "/addDES")
    @ApiOperation(value="DES加密")
    @ResponseBody
    public String addDES(AddDESViewForm addDESViewForm) {
        String word = addDESViewForm.getWord();
        String key = addDESViewForm.getKey();
        return customDESService.addDES(word,key);
    }

    /**
     * 解密
     * @param form
     * @return
     */
    @PostMapping(value = "/getWord")
    @ApiOperation(value="DES解密")
    @ResponseBody
    public String getWord(GetWordViewForm form) {
        return customDESService.getDESWord(form.getPass(),form.getKey());
    }
    /**
     * 通过id获取密文
     * @param form
     * @return
     */
    @PostMapping(value = "/getDESPassword")
    @ApiOperation(value="获取密文")
    @ResponseBody
    public String getDESPassword(GetDESViewForm form) {
        Long id = form.getId();
        return customDESService.getDESPassword(id);
    }
    /**
     * 通过id获取密文
     * @param form
     * @return
     */
    @PostMapping(value = "/getDESKey")
    @ApiOperation(value="获取密匙")
    @ResponseBody
    public String getDESKey(GetDESViewForm form) {
        Long id = form.getId();
        return customDESService.getDESKey(id);
    }
}
