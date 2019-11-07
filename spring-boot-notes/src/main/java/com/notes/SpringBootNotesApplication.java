package com.notes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.notes.*")
@ServletComponentScan(basePackages = "com.notes.*")
@EnableCaching
public class SpringBootNotesApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootNotesApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SpringBootNotesApplication.class, args);
        logger.info("notes网站启动成功---> http://localhost:8002/ ");
    }

}
