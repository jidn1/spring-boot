package com.notes.web;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.notes.common.MySseEmitter;
import com.notes.common.model.Results;
import com.notes.study.enums.SymbolTypeVO;
import com.notes.study.test.Student;
import com.oracle.tools.packager.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

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
    public String orgManageSave(@RequestBody @Valid SymbolTypeVO symbolTypeVO) {
        System.out.println(JSONObject.toJSONString(symbolTypeVO));


        return "hello world";
    }

    @ResponseBody
    @GetMapping("test")
    public Results test() {
        Student student = new Student();
//        for(int i =0;i<5;i++){
//            student.getName().add("tom"+i);
//            student.getAddress().add("beijing"+i);
//            student.getAge().add(""+i);
//        }
        String s = JSONObject.toJSONString(student);

        Map map = JSONObject.parseObject(s, Map.class);
        System.out.println(s);
        BeanMap beanMap = BeanMap.create(student);
        return Results.okMap(map);
    }


    public static Map<String, MySseEmitter> sseCache = new ConcurrentHashMap<>();
    @ResponseBody
    @GetMapping(value = "sse")
    public MySseEmitter sse() {
        MySseEmitter sseEmitter = new MySseEmitter();
        sseCache.put("id", sseEmitter);
        sseEmitter.onTimeout(() -> sseCache.remove("id"));
        sseEmitter.onCompletion(() -> System.out.println("completion"));
        return sseEmitter;
    }

    @ResponseBody
    @GetMapping(path = "pushsse")
    public void pushsse() {
        MySseEmitter sseEmitter = sseCache.get("id");
        if (sseEmitter != null) {
            try {
                sseEmitter.send("123123" + "\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
