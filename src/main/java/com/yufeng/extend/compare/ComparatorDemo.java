package com.yufeng.extend.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @description
 *      Comparator接口用法演示
 * @author yufeng
 * @create 2019-07-22
 */
public class ComparatorDemo {

    public static void main(String[] args) {
        comparatorTest01();
    }

    public static void comparatorTest01() {
        // 创建对象
        Person p1 = new Person(1, 18, "Java");
        Person p2 = new Person(2, 22, "MySQL");
        Person p3 = new Person(3, 8, "Spring");

        // 添加到集合
        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        // 进行排序操作(根据PersonComparator中定义的排序规则: 从小到大)
        Collections.sort(list, new PersonComparator());
        list.forEach(p -> System.out.println(p.getName() + ": " + p.getAge()));

        // 匿名内部类写法(从大到小)
        list.sort((Person o1, Person o2) -> o2.getAge() - o1.getAge());
        list.forEach(p -> System.out.println(p.getName() + ": " + p.getAge()));
    }

}

/**
 * 自定义Person类的比较器
 */
class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getAge() - o2.getAge();
    }
}
