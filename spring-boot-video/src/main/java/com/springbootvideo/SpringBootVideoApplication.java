package com.springbootvideo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.springbootvideo.*")
@ServletComponentScan(basePackages = "com.springbootvideo.*")
@EnableCaching
public class SpringBootVideoApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootVideoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootVideoApplication.class, args);
        logger.info("video网站启动成功---> http://localhost:8080/springboot_video ");
    }

}
