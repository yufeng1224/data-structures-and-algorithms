package com.yufeng.data.structure.set.test;

import com.yufeng.data.structure.set.impl.LinkedListSet;

import java.util.ArrayList;

/**
 * 描述:
 *      基于链表实现的集合测试类
 * @author yufeng
 * @create 2019-08-05
 */
public class LinkedListSetTest {

    public static void main(String[] args) {
        test01();
    }


    private static void test01() {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words1 = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            LinkedListSet<String> set1 = new LinkedListSet<>();
            for (String word : words1)
                set1.add(word);
            System.out.println("Total different words: " + set1.getSize());
        }
        System.out.println();

        System.out.println("A Tale of Two Cities");
        ArrayList<String> words2 = new ArrayList<>();
        if (FileOperation.readFile("a-tale-of-two-cities.txt", words2)) {
            System.out.println("Total words: " + words2.size());

            LinkedListSet<String> set2 = new LinkedListSet<>();
            for (String word : words2)
                set2.add(word);
            System.out.println("Total different words: " + set2.getSize());
        }
    }
}
