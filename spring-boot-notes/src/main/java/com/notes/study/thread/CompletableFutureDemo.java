package com.notes.study.thread;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CompletableFutureDemo {


    static Executor executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        List<CompletableFuture<Integer>> listCompletableFuture = new ArrayList<>();
        int i=0;
        while (i<10){
            listCompletableFuture.add(CompletableFuture.supplyAsync(() -> {
                        int count =0;
                        for(int j =0;j<10;j++){
                            try {
                                add(j);
                            } catch (Exception e) {
                                count ++;
                            }
                        }
                        return count;
                    }, executor)
            );
            i++;
        }
        CompletableFuture<Void> join = CompletableFuture.allOf(listCompletableFuture.toArray(new CompletableFuture[listCompletableFuture.size()]));

        try{
            List<Integer> collect = listCompletableFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
            int sum = collect.stream().mapToInt(Integer::intValue).sum();
            System.out.println(JSONObject.toJSONString(sum));
           // System.out.println(JSONObject.toJSONString(listCompletableFuture.get(0).get()));
        } catch (Exception e){}
    }



    public static void add(int j) throws Exception {
        if(j == 8){
            throw new Exception("error");
        }
    }
}
