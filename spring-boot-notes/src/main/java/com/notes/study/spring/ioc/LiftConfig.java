package com.notes.study.spring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = {"com.notes.study.spring.ioc"})
public class LiftConfig {

    @Bean
    public User getUserA(){
        return new User();
    }

    @Bean
    public User getUserA2(){
        return getUserA();
    }
}
