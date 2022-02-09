package com.yufeng.extend.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * @description
 *      Comparable接口用法演示
 * @author yufeng
 * @create 2019-07-22
 */
public class ComparableDemo {

    public static void main(String[] args) {
        comparableTest01();
        comparableTest02();
        comparableTest03();
    }

    public static void comparableTest01() {
        // 创建集合对象
        TreeSet<Student> ts = new TreeSet<>();

        // 创建学生对象
        Student s1 = new Student(25, "a");
        Student s2 = new Student(26, "b");
        Student s3 = new Student(27, "c");
        Student s4 = new Student(28, "d");

        Student s5 = new Student(28, "e");
        Student s6 = new Student(28, "e");

        ts.add(s1);
        ts.add(s2);
        ts.add(s3);
        ts.add(s4);
        ts.add(s5);
        ts.add(s6);                     // 无法存入, 保证元素的唯一性

        for (Student s : ts) {
            System.out.println(s.getName() + ": " + s.getAge());
        }
    }

    /**
     * 当自定义类Person没有Comparable时, List集合是没有排序的, 只能以元素的插入顺序
     * 作为输出的顺序
     */
    public static void comparableTest02() {
        // 创建对象
        Person p1 = new Person(1, 18, "Java");
        Person p2 = new Person(2, 22, "MySQL");
        Person p3 = new Person(3, 16, "Redis");

        // 添加到集合
        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        // 打印
        list.forEach(p -> System.out.println(p.getName() + " " + p.getAge()));
    }

    /**
     * 此时提出了一个新需求: 需要根据Person对象的年龄age属性进行倒序,
     * 也就是根据age属性从大到小进行排序, 此时就需要使用到Comparable
     */
    public static void comparableTest03() {
        // 创建对象
        Person p1 = new Person(1, 18, "Java");
        Person p2 = new Person(2, 22, "MySQL");
        Person p3 = new Person(3, 19, "Redis");

        // 添加到集合
        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        // 进行排序操作(根据Person类中的compareTo方法定义的排序规则)
        Collections.sort(list);

        // 打印
        list.forEach(p -> System.out.println(p.getName() + " " + p.getAge()));
    }
}
