package com.cn.school.controller.mzj;

import com.cn.school.FormView.AddDESViewForm;
import com.cn.school.FormView.GetDESViewForm;
import com.cn.school.FormView.GetWordViewForm;
import com.cn.school.service.mzj.CustomDESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author mzj
 * @date 2019-3-28 上午10:12:11
 */
@RestController
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
    @ResponseBody
    public String addDES(@RequestBody AddDESViewForm addDESViewForm) {
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
    @ResponseBody
    public String getWord(@RequestBody GetWordViewForm form) {
        return customDESService.getDESWord(form.getPass(),form.getKey());
    }
    /**
     * 通过id获取密文
     * @param form
     * @return
     */
    @PostMapping(value = "/getDESPassword")
    @ResponseBody
    public String getDESPassword(@RequestBody GetDESViewForm form) {
        Long id = form.getId();
        return customDESService.getDESPassword(id);
    }
    /**
     * 通过id获取密文
     * @param form
     * @return
     */
    @PostMapping(value = "/getDESKey")
    @ResponseBody
    public String getDESKey(@RequestBody GetDESViewForm form) {
        Long id = form.getId();
        return customDESService.getDESKey(id);
    }
}
