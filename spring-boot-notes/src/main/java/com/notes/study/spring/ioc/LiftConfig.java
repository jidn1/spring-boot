package com.notes.study.spring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
//@ComponentScan(basePackages = {"com.notes.study.spring.ioc"})
public class LiftConfig {

    @Bean
    private User getUserA(){
        return new User();
    }

    @Bean
    private User getUserA2(){
        return getUserA();
    }
}
