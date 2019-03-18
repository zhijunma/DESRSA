package com.cn.school.config;

import com.cn.school.aop.ApiParameterInterceptor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ApiParameterInterceptor apiParameterInterceptor() {
        return new ApiParameterInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 添加拦截规则
        // 添加需要拦截请求的路径
        registry.addInterceptor(apiParameterInterceptor())
                .addPathPatterns("/*")
                // 去除拦截请求的路径
                .excludePathPatterns("/login");
    }

}