package com.notes.study.spring.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainStart {


    // 当我们注册配置类的时候，可以不加Configuration注解，直接使用Component ComponentScan Import ImportResou rce注解，称之为Lite配置类
    // 如果加了Configuration注解，就称之为Full配置类
    // 如果我们注册了Lite配置类，我们getBean这个配置类，会发现它就是原本的那个配置类
    // 如果我们注册了Full配置类，我们getBean这个配置类，会发现它已经不是原本那个配置类了，而是已经被cgilb代理的 类了
    // 写一个A类，其中有一个构造方法，打印出“你好”
    // 再写一个配置类，里面有两个bean注解的方法
    // 其中一个方法new了A 类，并且返回A的对象，把此方法称之为getA
    // 第二个方法又调用了getA方法
    // 如果配置类是Lite配置类，会发现打印了两次“你好”，也就是说A类被new了两次
    // 如果配置类是Full配置类，会发现只打印了一次“你好”，也就是说A类只被new了一次，因为这个类被cgilb代理了，方法 已经被改写
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LiftConfig.class);

        User u = context.getBean( User.class);
    }
}
