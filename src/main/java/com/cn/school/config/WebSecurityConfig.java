package com.cn.school.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 功能描述:
 *
 * @param:
 * @return:
 * @auther: YiTong
 * @date: 2019/2/10 21:12
 */

/**
 * 开启注解验证  prePostEnabled
 * 这样就启动了JSR-250的注解支持，我们在方法上使用注解来控制访问权限。
 * 一、JSR-250注解
 *
 * @DenyAll 拒绝所有访问
 *
 * @RolesAllowed({"USER", "ADMIN"})  该方法只要具有"USER", "ADMIN"任意一种权限就可以访问。这里可以省略前缀ROLE_，实际的权限可能是ROLE_ADMIN
 *
 * @PermitAll 允许所有访问
 * 二、prePostEnabled注解
 *
 * 1、@PreAuthorize：在方法执行之前执行，而且这里可以调用方法的参数，也可以得到参数值，这是利用JAVA8的参数名反射特性，如果没用JAVA8，那么也可以利用Spring Security的@P标注参数，或者Spring Data的@Param标注参数。
 *
 * @PreAuthorize("#userId == authentication.principal.userId or hasAuthority(‘ADMIN’)")
 *
 * void changePassword(@P("userId") long userId ){  }
 *
 * 这里表示在changePassword方法执行之前，判断方法参数userId的值是否等于principal中保存的当前用户的userId，或者当前用户是否具有ROLE_ADMIN权限，两种符合其一，就可以访问该方法。
 *
 * 2、@PostAuthorize：在方法执行之后执行，而且这里可以调用方法的返回值，如果EL为false，那么该方法也已经执行完了，可能会回滚。EL变量returnObject表示返回的对象。
 *
 * @PostAuthorize
 *
 * User getUser("returnObject.userId == authentication.principal.userId or hasPermission(returnObject, 'ADMIN')");
 *
 * 3、@PostFilter：在方法执行之后执行，而且这里可以调用方法的返回值，然后对返回值进行过滤或处理或修改并返回。EL变量returnObject表示返回的对象。只有方法返回的是集合或数组类型的才可以使用。（与分页技术不兼容）
 *
 * @postFilter
 *
 * User getUser("hasPermission(returnObject, 'ADMIN')");
 *
 * 4、@PreFilter：在方法执行之前执行，而且这里可以调用方法的参数，然后对参数值进行过滤或处理或修改，EL变量filterObject表示参数，如有多个参数，使用filterTarget注解参数。只有方法参数是集合或数组才行。（很少会用到，与分页技术不兼容）
 */
@SuppressWarnings("ALL")
@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true, proxyTargetClass = true)
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public AppConfigUrl appConfigUrl;


    /**
     * 配置拦截器页面
     * 推荐使用注解
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] permits = appConfigUrl.getPermits();
        String secret = appConfigUrl.getSECRET();
        log.info("本次系统JWT秘钥:" + secret);
        for (String path : permits) {
            log.info("匿名访问路径" + path);
        }
        http.csrf().disable().authorizeRequests()
                .antMatchers(permits).permitAll()
                //开放路径
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/com/sendCode").permitAll()
                .anyRequest().authenticated()
                .and()
                // api/**目录
                .addFilterBefore(new JwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .antMatcher("/logout")
                .logout().deleteCookies("JSESSIONID");
    }

/*    @Autowired
    SysUserService sysUserService;

    @Override
    protected UserDetailsService userDetailsService() {
        return sysUserService;
    }*/
/*
   @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/static/**");
    }*/

}

