package com.yufeng.data.structure.hashTable.test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 描述:
 *      jdk中的hashCode
 * @author yufeng
 * @create 2019-09-10
 */
public class HashCodeTest {

    public static void main(String[] args) {

        int a = 42;
        System.out.println(((Integer)a).hashCode());    // 42

        int b = -42;
        System.out.println(((Integer)b).hashCode());    // -42

        double c = 3.1415926;
        System.out.println(((Double)c).hashCode());     // 219937201

        String d = "imooc";
        System.out.println(d.hashCode());               // 100327135

        Student student = new Student(3, 2, "bobo", "liu");
        System.out.println(student.hashCode());

        Student student2 = new Student(3, 2, "BOBO", "liu");
        System.out.println(student2.hashCode());


        // jdk中的hash应用
        HashSet<Student> set = new HashSet<>();
        set.add(student);

        HashMap<Student, Integer> scores = new HashMap<>();
        scores.put(student, 100);

        Student student3 = new Student(3, 2, "BOBO", "liu");
        System.out.println(student3.hashCode());            // 注释掉 Student 中的hashCode, 根据每个对象的地址映射成整型
    }
}
