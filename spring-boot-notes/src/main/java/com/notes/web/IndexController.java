package com.notes.web;

import com.alibaba.fastjson.JSONObject;
import com.notes.common.model.Results;
import com.notes.study.enums.SymbolTypeVO;
import com.notes.study.test.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/7 10:19
 * @Description:
 */

@Api(tags = "合伙人购买大厅 黄一鸣")
@Controller
@RequestMapping("/")
public class IndexController {

    @ApiOperation(value = "首页 index", httpMethod = "GET", response = HashMap.class, notes = "首页 index")
    @GetMapping("index")
    public String index(HttpServletRequest request) {

        return "index";
    }


    @ResponseBody
    @PostMapping("symbol")
    public String orgManageSave( @RequestBody @Valid SymbolTypeVO symbolTypeVO) {
        System.out.println(JSONObject.toJSONString(symbolTypeVO));


        return "hello world";
    }

    @ResponseBody
    @GetMapping("test")
    public Results test( ) {
        Student student = new Student();
        for(int i =0;i<5;i++){
            student.getName().add("tom"+i);
            student.getAddress().add("beijing"+i);
            student.getAge().add(""+i);
        }
        String s = JSONObject.toJSONString(student);

        Map map = JSONObject.parseObject(s, Map.class);
        System.out.println(s);
        BeanMap beanMap = BeanMap.create(student);
        return Results.okMap(map);
    }


    @ResponseBody
    @GetMapping("streaming")
    public  String streaming(HttpServletRequest request,
                             HttpServletResponse response) {
        String result = "";//请求返回参数
        String jsonString = "";//获得jsonstirng,或者toString都可以，只要是json格式，给了别人能解析成json就行
        try {

            //定义输出流，有什么数据要发送的，直接后面append就可以，记得转成byte再append
            OutputStream out = response.getOutputStream();


            StringBuilder sb = new StringBuilder();
            //把要传的json字符串放进来
            sb.append(jsonString);
            out.write(sb.toString().getBytes("utf-8"));
            out.write("\n".getBytes("utf-8"));

            //发送流
            out.flush();
            out.close();
//            // 定义BufferedReader输入流来读取URL的响应
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    conn.getInputStream()));
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                result += line;
//            }
//            System.out.println("================");
//            System.out.println(result.toString());//可以把结果打印出来瞅瞅
//            System.out.println("================");
            //后面可以对结果进行解析（如果返回的是格式化的数据的话）
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        return result;
    }
}
