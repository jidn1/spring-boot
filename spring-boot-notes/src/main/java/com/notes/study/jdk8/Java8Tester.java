package com.notes.study.jdk8;

import lombok.Builder;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jidn
 * @Date 2020/9/21
 */
public class Java8Tester {


    public static void main(String[] args) {

        // 计算空字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);

        Random random = new Random();


        System.out.println("使用 Java 8: ");
        System.out.println("列表: " +strings);

        long count = strings.stream().filter(string->string.isEmpty()).count();
        System.out.println("空字符串数量为: " + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为 3 的数量为: " + count);

        List<String>  filtered = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表: " + filtered);

        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        List<Integer> squaresList = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
        System.out.println("Squares List: " + squaresList);
        System.out.println("列表: " +integers);

        IntSummaryStatistics stats = integers.stream().mapToInt((x) ->x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("随机数: ");

        random.ints().limit(10).sorted().forEach(System.out::println);

        // 并行处理
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串的数量为: " + count);


//        strings.stream().filter(f->f.length() == 3).collect(Collectors.groupingBy(f));

        List<Student> list = new ArrayList<>();

        Student student = Student.builder().userId(2).name("tom").address("bejing").build();
        Student student1 = Student.builder().userId(-1).name("jack").address("shanghai").build();
        Student student2 = Student.builder().userId(3).name("thon").address("tianjin").build();

        list.add(student);
        list.add(student1);
        list.add(student2);

        Map<Integer, List<Student>> collect = list.stream().filter(u -> u.getUserId() != -1).collect(Collectors.groupingBy(Student::getUserId));


        System.out.println(collect);

        Student.builder().name("jidn").address("");





    }


    @Builder
    @Data
    static class Student {


        private String name;

        private String address;

        private int userId;




    }

}
