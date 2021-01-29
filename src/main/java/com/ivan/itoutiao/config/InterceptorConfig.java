package com.ivan.itoutiao.config;

import com.ivan.itoutiao.filter.LoginInterceptor;
import com.ivan.itoutiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Ivan
 * @Title: InterceptorConfig 注册拦截器
 * @date 2021/1/2915:31
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    UserService userService;

    public void addInterceptors(InterceptorRegistry registry) {
        //此处配置拦截路径
        registry.addInterceptor(new LoginInterceptor(userService)).
                addPathPatterns("*").
                excludePathPatterns("/login","/logout");
    }



}
