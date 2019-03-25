package com.cn.school.aop;

import com.cn.school.config.MyHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by
 */
@Slf4j
@WebFilter(filterName = "myFilter", urlPatterns = "/web/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("--------------过滤器初始化------------");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("--------------执行过滤操作------------");

        ServletRequest requestWrapper = null;
        if(request instanceof HttpServletRequest) {
            requestWrapper = new MyHttpServletRequestWrapper((HttpServletRequest) request);
        }
        if(requestWrapper == null) {
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(requestWrapper, response);
        }

    }

    @Override
    public void destroy() {
        System.out.println("--------------过滤器销毁------------");
    }
}

