package com.ivan.itoutiao.config;

import com.ivan.itoutiao.filter.LoginInterceptor;
import com.ivan.itoutiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Ivan
 * @date 2020/12/31 12:55
 * @Description: 配置类
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //文件存放的路径
    @Value("${file.path}")
    private String filePath;

    @Autowired
    UserService userService;

    public void addInterceptors(InterceptorRegistry registry) {
        //此处配置拦截路径
        registry.addInterceptor(new LoginInterceptor(userService)).
                addPathPatterns("/**").
                excludePathPatterns("/user/login").//过滤登录接口
                excludePathPatterns("/file/**");//文件资源
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:///" + filePath);
    }

}
