package com.yufeng.extend.compare;

/**
 * @description
 *      实体类
 * @author yufeng
 * @create 2019-07-22
 */
public class Person implements Comparable<Person> {

    private int id;

    private int age;

    private String name;

    public Person(int id, int age, String name) {
        this.id = id;
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
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person p) {
        return p.getAge() - this.getAge();
    }
}
