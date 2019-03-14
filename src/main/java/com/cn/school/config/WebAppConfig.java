//package com.cn.school.config;
//
//import com.cn.school.aop.CommonInterceptorl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * @author:HuMin Date:2019/3/13
// * Time:11:19
// */
//@Configuration
//public class WebAppConfig extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
//        //通過路徑
////        addInterceptor.excludePathPatterns("/login");
//        //拦截路径
//        addInterceptor.addPathPatterns("/**");
//    }
//
//    @Bean
//    public CommonInterceptorl getSecurityInterceptor() {
//        return new CommonInterceptorl();
//    }
//}