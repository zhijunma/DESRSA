package com.cn.school.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName: Swagger2 
 * @Description: 接口测试页面
 * @author 易通
 * @date 2018年12月9日 上午9:54:25 
 * @version V1.0 
 */
@Configuration
@EnableSwagger2
public class Swagger2 extends WebMvcConfigurerAdapter {
    //swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    @Autowired
    public AppConfigUrl appConfigUrl;

    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar
                .name("Content-type").description("Json格式").defaultValue("application/json;charset=UTF-8")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false)
                .build(); //header中的ticket参数非必填，传空也可以

        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.globalOperationParameters(pars)
                .select()

                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.cn.school.controller"))
                .paths(PathSelectors.any())

                .build();
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(new String[]{"classpath:/META-INF/resources/", "classpath:/static/"}).setCachePeriod(0);
    }

    /**
     * 注册默认转发到index页面
     * @param
     *//*
    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/index.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }*/

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("接口测试页面")
                //创建人
                .contact(new Contact("雷云龙", "http://yunlong.xyz", "yunlonglei0@gmail.com"))
                //版本号
                .version("1.0")
                //描述
                .description("API接口测试")
                .build();
    }



}