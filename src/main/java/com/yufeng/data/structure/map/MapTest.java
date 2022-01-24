package com.yufeng.data.structure.map;

import com.yufeng.data.structure.map.impl.BSTMap;
import com.yufeng.data.structure.map.impl.LinkedListMap;
import com.yufeng.data.structure.set.FileOperation;

import java.util.ArrayList;

/**
 * @description
 *      映射测试类
 * @author yufengl
 * @create 2019-07-17
 */
public class MapTest {

    /**
     * 链表映射测试
     */
    public void linkedListMapTest() {
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

    /**
     * 二分搜索树映射测试
     */
    public void bstMapTest() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BSTMap<String, Integer> bstMap = new BSTMap<>();

            for (String word : words) {
                if (bstMap.contains(word)) {
                    bstMap.set(word, bstMap.get(word) + 1);
                } else {
                    bstMap.add(word, 1);
                }
            }

            System.out.println("BSTMap -- Total different words: " + bstMap.getSize());
            System.out.println("BSTMap -- Frequency of PRIDE: " + bstMap.get("pride"));
            System.out.println("BSTMap -- Frequency of PREJUDICE: " + bstMap.get("prejudice"));
        }
    }

    public void bstMapTest2() {
        BSTMap<Integer, Integer> bstMap = new BSTMap<>();
        int[] nums = {10, 5, 4, 8, 13, 12, 16, 14, 11};

        for (int num : nums) {
            bstMap.add(num, num);
        }

        // ***********************//
        //          10            //
        //         /   \          //
        //       5      13        //
        //     /   \   /   \      //
        //    4     8 12   16     //
        //            /    /      //
        //           11    14     //
        // ********************** //


        System.out.println(bstMap.getSize());
        bstMap.remove(13);
        System.out.println(bstMap.getSize());
    }

    /**
     * LinkedListMap、BSTMap性能比较
     */
    public void compareMap() {
        String filename = "pride-and-prejudice.txt";

        BSTMap<String, Integer> bstMap = new BSTMap<>();
        double time1 = testMap(bstMap, filename);
        System.out.println("BST Map: " + time1 + " s");                     // BST Map: 0.218165727 s

        System.out.println();

        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        double time2 = testMap(linkedListMap, filename);
        System.out.println("Linked List Map: " + time2 + " s");             // Linked List Map: 11.268514581 s
    }

    public double testMap(Map<String, Integer> map, String filename) {
        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words) {

                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }
        }

        System.out.println("Total different words: " + map.getSize());
        System.out.println("Frequency of PRIDE: " + map.get("pride"));
        System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        MapTest mapTest = new MapTest();

        mapTest.linkedListMapTest();
        mapTest.bstMapTest();
        mapTest.bstMapTest2();
        mapTest.compareMap();
    }
}
