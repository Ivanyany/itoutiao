package com.ivan.itoutiao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:///" + filePath);
    }

}
