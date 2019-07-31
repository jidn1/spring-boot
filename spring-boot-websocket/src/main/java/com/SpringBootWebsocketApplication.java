package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.*")
@ServletComponentScan(basePackages = "com.*")
public class SpringBootWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebsocketApplication.class, args);
    }

}
