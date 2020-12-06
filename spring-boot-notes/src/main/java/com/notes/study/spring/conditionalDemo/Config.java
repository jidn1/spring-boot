package com.notes.study.spring.conditionalDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.notes.study.spring.conditionalDemo"})
public class Config {

    @Bean
    @Conditional(WindowsCondition.class)
    public ListService window(){
        return new WindowsService();
    }

    @Bean("macService")
    @Conditional(MacCondition.class)
    public ListService mac(){
        return new MacService();
    }

    @Bean("linuxService")
    @Conditional(LinuxCondition.class)
    public ListService linux(){
        return new LinuxService();
    }
}
