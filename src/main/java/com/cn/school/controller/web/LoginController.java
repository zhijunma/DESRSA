package com.cn.school.controller.web;

import com.cn.school.config.JwtUtil;
import com.cn.school.entity.DSUser;
import com.cn.school.service.web.impl.LoginServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private Respon respon;

    @PostMapping(value = "loginPage")
    public Respon LoginCon(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestBody @Validated Account account) {
        String username = account.username;
        String password = account.password;
        DSUser user = loginService.Login(username, password);
        //生成token
        String token = jwtUtil.generateToken(user, response, request);
        //封装的实体类里setToken
        respon.setToken(token);

        HashMap<Object, Object> data = new HashMap<>();
        data.put("username", username);
        data.put(JwtUtil.appConfigUrl.getHEADER_STRING(), token);
        session.setAttribute("username", username);
        respon.setData(data);
        return respon;
    }
    //@ApiModel(value = "账号")
    public static class Account {
        //@Email(message = "请输入正确的邮箱")
        @NotBlank(message = "username不能为空")
        public String username;
        @NotNull(message = "密码不能为空")
        @NotBlank(message = "密码不能为空")
        public String password;
    }

    @Setter
    @Getter
    @Component
    class Respon {

        public String token;
        public Map data;
    }

}