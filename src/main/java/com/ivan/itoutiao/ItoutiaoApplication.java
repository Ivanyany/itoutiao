package com.ivan.itoutiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ivan"})//扫描包
public class ItoutiaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItoutiaoApplication.class, args);
    }

}
