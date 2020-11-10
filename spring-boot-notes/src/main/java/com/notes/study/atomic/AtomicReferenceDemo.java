package com.notes.study.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {

    public static void main(String[] args) {

        User aaa = new User("aaa", 20);
        User bbb = new User("bbb", 30);

        // 创建原子引用包装类
        AtomicReference<User> atomicReference = new AtomicReference<>();
        // 现在主物理内存的共享变量，为aaa
        atomicReference.set(aaa);

        // 比较并交换，如果现在主物理内存的值为aaa，那么交换成bbb
        System.out.println(atomicReference.compareAndSet(aaa, bbb) + "\t " + atomicReference.get().toString());
        // 比较并交换，现在主物理内存的值是bbb了，但是预期为aaa，因此交换失败
        System.out.println(atomicReference.compareAndSet(aaa, bbb) + "\t " + atomicReference.get().toString());
    }

}


@Data
@AllArgsConstructor
class User {
    String userName;
    int age;
}