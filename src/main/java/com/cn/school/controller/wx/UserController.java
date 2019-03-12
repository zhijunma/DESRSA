package com.cn.school.controller.wx;

import com.cn.school.dto.forms.WxInsertUserViewForm;
import com.cn.school.service.wx.UserService;
import com.cn.school.utils.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 用户注册
     *
     * @return
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public RestResponse register(@Validated @RequestBody WxInsertUserViewForm viewForm) {
        return userService.insertUser(viewForm);
    }

    /**
     * 报名
     *
     * @return
     */
    @RequestMapping(value = "/addUser")
    @ResponseBody
    public RestResponse addUser(@Validated @RequestBody WxInsertUserViewForm viewForm) {
        return userService.insertUser(viewForm);
    }

    @RequestMapping("loginInit.do")
    public String loginInit(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //回调地址,要跟下面的地址能调通(getWechatGZAccessToken.do)
        String backUrl = "http://3261045722cwy.vicp.io/wx/getWechatGZAccessToken.do";
        /**
         *这儿一定要注意！！首尾不能有多的空格（因为直接复制往往会多出空格），其次就是参数的顺序不能变动
         **/
        //AuthUtil.APPID微信公众号的appId
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + "wx873d087d4a824e07" +
                "&redirect_uri=" + URLEncoder.encode(backUrl, "UTF-8") +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        return "redirect:" + url;

    }

}
