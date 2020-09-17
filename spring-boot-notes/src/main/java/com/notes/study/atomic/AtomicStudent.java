package com.notes.study.atomic;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author jidn
 * @Date 2020/9/16
 */
public class AtomicStudent {


    private String name;

    private int age;

    public AtomicStudent(String name, int age){
        this.name = name;
        this.age = age;
    }


    public static void main(String[] args) {
        AtomicStudent a = new AtomicStudent("tom",18);

        System.out.println(ClassLayout.parseInstance(a).toPrintable());


    }

}
