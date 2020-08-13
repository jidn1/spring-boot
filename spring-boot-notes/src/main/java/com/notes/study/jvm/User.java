package com.notes.study.jvm;

import java.io.Serializable;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2020/8/4 17:50
 * @Description:
 */
public class User implements Serializable {


    public User (){

    }


    private String name;


    private String address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void sout(){
        System.out.println("这是一个自己实现的类加载器");
    }
}
