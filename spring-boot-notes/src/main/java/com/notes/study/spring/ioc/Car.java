package com.notes.study.spring.ioc;

import org.springframework.stereotype.Component;

@Component
public class Car {

    public Car(){
        System.out.println("Car 初始化加载");
    }

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
