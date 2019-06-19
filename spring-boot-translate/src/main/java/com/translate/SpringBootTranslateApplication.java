package com.translate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.translate.*")
@SpringBootApplication
public class SpringBootTranslateApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootTranslateApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTranslateApplication.class, args);

        logger.info("translate启动成功---> http://localhost:8002/index");
    }

}
