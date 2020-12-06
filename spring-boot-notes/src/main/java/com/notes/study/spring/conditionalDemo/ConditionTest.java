package com.notes.study.spring.conditionalDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConditionTest {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        ListService listService = (ListService)context.getBean("macService");

        System.out.println(context.getEnvironment().getProperty("os.name") + "系统下的列表命令为：" + listService.showListCmd());
    }
}
