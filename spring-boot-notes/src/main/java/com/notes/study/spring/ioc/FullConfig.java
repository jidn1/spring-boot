package com.notes.study.spring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration(proxyBeanMethods = false)
//@ComponentScan(basePackageClasses = {User.class})
public class FullConfig {

    @Bean
    public User getUserA(){
        return new User();
    }

    @Bean
    public User getUserA2(){
        return getUserA();
    }
}
