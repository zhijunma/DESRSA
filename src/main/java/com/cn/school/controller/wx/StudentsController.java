package com.cn.school.controller.wx;

import com.cn.school.dto.forms.students.AddInfoViewForm;
import com.cn.school.dto.forms.students.AddStudentsViewForm;
import com.cn.school.service.wx.StudentsService;
import com.cn.school.utils.request.RestRequest;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 报名
 *
 * @author:HuMin Date:2019/3/1
 * Time:15:25
 */
@Controller
@RequestMapping("/wx/user")
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    /**
     * 报名
     *
     * @return
     */
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public RestResponse register(@Validated @RequestBody RestRequest<AddStudentsViewForm> request) {
        AddStudentsViewForm viewForm = request.getBody();
        return studentsService.addStudents(viewForm);
    }

    /**
     * 添加报名信息
     *
     * @return
     */
    @PostMapping(value = "/addInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public RestResponse addInfo(@Validated @RequestBody RestRequest<AddInfoViewForm> request) {
        AddInfoViewForm viewForm = request.getBody();
        return studentsService.addInfo(viewForm);
    }

    @RequestMapping("loginInit.do")
    public String loginInit(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //回调地址,要跟下面的地址能调通(getWechatGZAccessToken.do)
        String backUrl = "http://3261045722cwy.vicp.io/wx/getWechatGZAccessToken.do";
        /**
         *这儿一定要注意！！首尾不能有多的空格（因为直接复制往往会多出空格），其次就是参数的顺序不能变动
         **/
        //AuthUtil.APPID微信公众号的appId
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + "wx7a643cf968956196" +
                "&redirect_uri=" + URLEncoder.encode(backUrl, "UTF-8") +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        return "redirect:" + url;

    }

}
