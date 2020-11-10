package com.notes.study.spring;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author jidn
 * @Date 2020/9/27
 */
public class Demo {


    static HashMap<Integer, List<Integer>> cache = new HashMap<>();

    public static void main(String[] args) {
        List one = Arrays.asList(222, 333, 444, 555, 123, 122, 121, 124);
        List two = Arrays.asList(666, 777, 888, 999, 454, 341, 834, 901);
        List three = Arrays.asList(234, 782, 526, 452, 643, 721, 453, 589);

        cache.put(9001, one);
        cache.put(9002, two);
        cache.put(9003, three);

        int child = findChild(9001);
        System.out.println(child);
        System.out.println(findMain(child));

    }


    public static int findMain(int child) {
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = cache.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> entry = iterator.next();
            if (entry.getValue().contains(child)) {
                return entry.getKey();
            }
        }
        return 0;
    }

    public static int findChild(int main) {
        List<Integer> integerList = cache.get(main);
        Random rand = new Random();
        return integerList.get(rand.nextInt(integerList.size()));
    }


}
