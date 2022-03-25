package com.yufeng.data.structure.hashTable.test;

/**
 * @description
 *      Java中的hashCode
 * @author yufeng
 * @create 2019-08-08
 */
public class Student {

    int grade;

    int cls;

    String firstName;

    String lastName;

    public Student(int grade, int cls, String firstName, String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * 自定义hash函数
     */
    @Override
    public int hashCode() {
        int B = 31;

        int hash = 0;
        hash = hash * B + grade;
        hash = hash * B + cls;
        hash = hash * B + firstName.toLowerCase().hashCode();
        hash = hash * B + lastName.toLowerCase().hashCode();

        // 如果产生整型溢出, 计算机会进行处理
        return hash;
    }

    /**
     * 如果hash冲突, 还需要通过equals()方法来进行判断
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Student another = (Student)obj;
        return this.grade == another.grade &&
                this.cls == another.cls &&
                this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) &&
                this.lastName.toLowerCase().equals(another.lastName.toLowerCase());
     }
}
