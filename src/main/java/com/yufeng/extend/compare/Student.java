package com.yufeng.extend.compare;

/**
 * @description
 *      实体类
 * @author yufeng
 * @create 2019-07-22
 */
public class Student implements Comparable<Student> {

    private int age;

    private String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int compareTo(Student s) {
        //return 0;                     // 后面新添加的元素不会存入
        //return 1;                     // 按升序添加
        //return -1;                    // 按降序添加

        int num = this.age - s.age;     // 年龄按照从小到大排序
        //int num = s.age - this.age;   // 年龄按照从大到小排序

        if (num == 0) {                 // 年龄相同则比较姓名
            num = this.name.compareTo(s.name);
        }
        return num;
    }
}
