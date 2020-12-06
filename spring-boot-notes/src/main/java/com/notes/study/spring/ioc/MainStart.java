package com.notes.study.spring.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.auditing.config.AnnotationAuditingConfiguration;

public class MainStart {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

        Car car = context.getBean("car", Car.class);
        System.out.println(car.getName());
    }
}
