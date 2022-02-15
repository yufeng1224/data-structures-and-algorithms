package com.yufeng.algorithm.linearSearch;

/**
 * @description
 *      实体类
 * @author yufeng
 * @create 2019-10-10
 */
public class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    /**
     * 固定套路写法
     */
    @Override
    public boolean equals(Object student) {
        if (this == student) {                  // 地址值是否相同
            return true;
        }

        if (student == null) {
            return false;
        }

        if (this.getClass() != student.getClass()) {
            return false;
        }

        Student another = (Student)student;
        return this.name.equalsIgnoreCase(another.name);

    }
}
