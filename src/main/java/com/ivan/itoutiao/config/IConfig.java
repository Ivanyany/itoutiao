package com.ivan.itoutiao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ivan
 * @date 2020/12/31 12:55
 * @Description: 配置类
 */
@Configuration
@MapperScan("com.ivan.itoutiao.mapper")
public class IConfig {
}
