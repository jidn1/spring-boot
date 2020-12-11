package com.notes.study.spring.ioc.beanScope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        annotationConfigApplicationContext.destroy();
    }
}
