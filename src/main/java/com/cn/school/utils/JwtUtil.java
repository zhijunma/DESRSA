package com.cn.school.utils;

import com.cn.school.config.AppConfigUrl;
import com.cn.school.entity.DSUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

/**
 * 功能描述:   jwt工具包
 *
 * @param:
 * @return:
 * @auther: YiTong
 * @date: 2019/2/10 22:57
 */
@Component
@Slf4j
@Data
public class JwtUtil {

    public static ApplicationContext applicationContext;


    /**
     * 配置文件
     */
    public static AppConfigUrl appConfigUrl;

    @Autowired
    public JwtUtil(AppConfigUrl appConfigUrl, ApplicationContext applicationContext) {
        JwtUtil.appConfigUrl = appConfigUrl;
        JwtUtil.applicationContext = applicationContext;
    }


    /**
     * 生成令牌
     *
     * @param user
     * @return
     */
    public String generateToken(DSUser user) {
        //添加自定义参数
        HashMap<String, Object> map = new HashMap<>();
        /**
         * 时间限制
         */
        Date date = new Date(System.currentTimeMillis() + appConfigUrl.getEXPIRATION_TIME());

        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(date)
                //用户
                .setSubject(user.getUserName())
                //用户id
                .setId(user.getUserName())
                //设置cokice
                .signWith(SignatureAlgorithm.HS512, appConfigUrl.getSECRET())
                .compact();
        return jwt;
    }

    /**
     * 功能描述: 获取Tokem
     *
     * @param:
     * @return:
     */
    public static String getToken(HttpServletRequest request) {

        String token = request.getHeader(appConfigUrl.getHEADER_STRING());
        if (token == null) {
            token = request.getParameter(appConfigUrl.getACCESS_TOKEN());
            return token;
        } else if (token == null) {
            throw new TokenValidationException(appConfigUrl.getHEADER_STRING() + ":令牌错误!" + "无权访问,原因:令牌过时或错误请重新登录获取令牌");
        } else {
            return token;
        }

    }

    /**
     * 验证令牌
     *
     * @param request
     * @return
     */
    public static Jws<Claims> validateTokenAndGetClaims(HttpServletRequest request) {
        String token = request.getHeader(appConfigUrl.getHEADER_STRING());
        if (token == null) {
            token = request.getParameter(appConfigUrl.getACCESS_TOKEN());
        }
        if (token == null) {
            throw new TokenValidationException(appConfigUrl.getHEADER_STRING() + ":令牌错误!" + "无权访问,原因:令牌过时或错误请重新登录获取令牌");
        }
        //令牌验证
        Jws<Claims> body = Jwts.parser()
                .setSigningKey(appConfigUrl.getSECRET())
                .parseClaimsJws(token.replace(appConfigUrl.getTOKEN_PREFIX(), ""));

        //时间控制jwt已经很完善,不要验证
        //这里可以验证自定义的令牌,检查是否是唯一登录等等
        /*Date expiration = body.getBody().getExpiration();
        final long l = expiration.getTime() - System.currentTimeMillis();

        if (l <0)
            throw new TokenValidationException(HEADER_STRING+":令牌错误!"+"无权访问,原因:令牌过时或错误请重新登录获取令牌");*/
        return body;
    }

    /**
     * 自定义异常处理
     */
    static class TokenValidationException extends RuntimeException {
        public TokenValidationException(String msg) {
            super(msg);
        }
    }
}