package com.cn.school.controller.mzj;

import com.cn.school.FormView.AddRSAViewForm;
import com.cn.school.FormView.GetRSAViewForm;
import com.cn.school.service.mzj.CustomRSAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mzj Date:4.22
 * Time 21:13
 */
@RestController
@RequestMapping("/customRSA")
public class CustomRSAController {
    @Autowired
    CustomRSAService customRSAService;

    /**
     * 加密
     * @param addRSAViewForm
     * @return
     */
    @PostMapping(value = "/addRSA")
    public String addRSA(@RequestBody AddRSAViewForm addRSAViewForm) {
        String word = addRSAViewForm.getWord();
        Integer keySize = addRSAViewForm.getKeySize();
        return customRSAService.addRSA(word,keySize);
    }
    /**
     * 解密
     * @param form
     * @return
     */
    @PostMapping(value = "/getRSA")
    public String getESA(@RequestBody GetRSAViewForm form) {
        return customRSAService.getRSA(form);
    }
}
