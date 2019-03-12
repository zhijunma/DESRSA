package com.cn.school.config;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: 令牌验证
 *
 * @auther: YiTong
 * @date: 2019/2/10 9:25
 */
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    /**
     * 验证路径正则匹配
     */
    private static final PathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 需要验证的
     */
    private String protectUrlPattern = "/**";

    /**
     * 拦截器
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
log.info(request.getParameter("access_token"));
        try {
            if (isProtectedUrl(request)) {
                Jws<Claims> claims = JwtUtil.validateTokenAndGetClaims(request);
                //获取用户id
                String username = claims.getBody().getId();
                //查找用户
                UserDetails user = JwtUtil.getUser(username);
                /* 查找用户结束*/
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                user, JwtUtil.getToken(request), user.getAuthorities()));

            }
            //放行通过
            filterChain.doFilter(request, response);
            return;
        } catch (JwtUtil.TokenValidationException e) {
            respjson(e, response, 401);
            return;
        } catch (Exception e) {
            respjson(e, response, 402);
            return;
        }

    }

    public void respjson(Exception e, HttpServletResponse response, int code) {
        e.printStackTrace();
        response.setStatus(200); //设置好相应的状态
        //设置用户取消验证后的消息提示
        response.setContentType("application/json; charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        map.put("status", code);
        map.put("msg", "登录过期");
        map.put("message", e.getMessage());
        String s = JSON.toJSONString(map);
        try {
            response.getWriter().print(s);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        log.info(s);
    }

    /**
     * 验证token请求 url
     *
     * @param request
     * @return
     */
    private boolean isProtectedUrl(HttpServletRequest request) {

        return pathMatcher.match(protectUrlPattern, request.getServletPath());
    }


}