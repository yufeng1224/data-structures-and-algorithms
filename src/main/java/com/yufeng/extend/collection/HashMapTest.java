package com.yufeng.extend.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @description
 *      HashMap 测试类
 * @author yufeng
 * @create 2019-07-16
 */
public class HashMapTest {

    private Map<Integer, String> map = new HashMap<>();
    {
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java社区");
    }

    /**
     * 使用EntrySet 迭代器遍历
     */
    public void test1() {
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * 使用keySet 迭代器遍历
     */
    public void test2() {
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.println(key + " : " + map.get(key));
        }
    }

    /**
     * 使用foreach 遍历Entry
     */
    public void test3() {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * 使用foreach 遍历keySet
     */
    public void test4() {
        for (Integer key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
    }

    public static void main(String[] args) {
        HashMapTest hashMapTest = new HashMapTest();

        hashMapTest.test1();
        System.out.println("----------------------------------------");

        hashMapTest.test2();
        System.out.println("----------------------------------------");

        hashMapTest.test3();
        System.out.println("----------------------------------------");

        hashMapTest.test4();
        System.out.println("----------------------------------------");

        /**
         * 知识点总结
         *    1. EntrySet性能比keySet高
         *    2. 迭代器中循环删除数据是安全的
         *    3. for循环、lambda方式、Stream方式 删除数据都会抛出异常
         */
    }
}
