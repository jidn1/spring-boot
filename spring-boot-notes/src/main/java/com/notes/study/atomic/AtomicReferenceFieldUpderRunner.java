package com.notes.study.atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author jidn
 * @Date 2020/9/16
 */
public class AtomicReferenceFieldUpderRunner {


    static AtomicReferenceFieldUpdater fieldUpdater = AtomicReferenceFieldUpdater.newUpdater(User.class, String.class, "name");

    public static void main(String[] args) {

        User us = new User("tom", 18);
        System.out.println(fieldUpdater.get(us));
        fieldUpdater.getAndSet(us, "jack");
        System.out.println(fieldUpdater.get(us));

    }


    static class User {
        public volatile String name;
        public  int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

    }

}


