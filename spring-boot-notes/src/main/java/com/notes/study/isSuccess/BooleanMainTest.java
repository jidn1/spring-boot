package com.notes.study.isSuccess;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.StringJoiner;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2020/3/5 14:20
 * @Description:
 */
public class BooleanMainTest {

    public static void main(String[] args) throws Exception{
        Model model1 = new Model();
        System.out.println("default model : " + model1);

        Model3 model3 = new Model3();
        //使用fastjson(1.2.16)序列化model3成字符串并输出
        System.out.println("Serializable Result With fastjson :" + JSON.toJSONString(model3));

        //使用Gson(2.8.5)序列化model3成字符串并输出
//        Gson gson =new Gson();
//        System.out.println("Serializable Result With Gson :"+gson.toJson(model3));

        //使用jackson(2.9.7)序列化model3成字符串并输出
        ObjectMapper om = new ObjectMapper();
        System.out.println("Serializable Result With jackson :" +om.writeValueAsString(model3));


    }
}

class  Model{
    private Boolean success;

    private boolean failure;

    @Override
    public String toString() {
        return new StringJoiner(", ", Model.class.getSimpleName() + "[","]")
                .add("success=" + success)
                .add("failure=" + failure)
                .toString();
    }

}



class Model1  {
    private Boolean isSuccess;
    public void setSuccess(Boolean success) {
        isSuccess = success;
    }
    public Boolean getSuccess() {
        return isSuccess;
    }
}

class Model2 {
    private Boolean success;
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
}

class Model3 {
    private static final long serialVersionUID = 1836697963736227954L;
    private boolean isSuccess;

    public boolean getIsSuccess() {
        return isSuccess;
    }
//    public void setSuccess(boolean success) {
//        isSuccess = success;
//    }
    public String getHollis(){
        return "hollischuang";
    }
}

class Model4 {
    private boolean success;
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
