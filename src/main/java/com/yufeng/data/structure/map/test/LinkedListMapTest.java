package com.yufeng.data.structure.map.test;

import com.yufeng.data.structure.map.impl.LinkedListMap;
import com.yufeng.data.structure.set.FileOperation;

import java.util.ArrayList;

/**
 * 描述:
 *     基于链表实现的映射测试类
 * @author yufeng
 * @create 2019-08-14
 */
public class LinkedListMapTest {

    public static void main(String[] args) {
        test01();
    }


    private static void test01() {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());
            LinkedListMap<String, Integer> map = new LinkedListMap<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }

            System.out.println("Total different words: " + map.getSize());          // Total different words: 6530
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));  // Frequency of PREJUDICE: 11
            System.out.println("Frequency of PRIDE: " + map.get("pride"));          // Frequency of PRIDE: 53
        }
    }
}
