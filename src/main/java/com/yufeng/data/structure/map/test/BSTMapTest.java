package com.yufeng.data.structure.map.test;

import com.yufeng.data.structure.map.impl.BSTMap;
import com.yufeng.data.structure.set.test.FileOperation;

import java.util.ArrayList;

/**
 * 描述:
 *      基于二分搜索树实现的映射测试类
 * @author yufeng
 * @create 2019-08-14
 */
public class BSTMapTest {

    public static void main(String[] args) {
        bstMapTest01();

        bstMapTest02();
    }


    private static void bstMapTest01() {
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


    private static void bstMapTest02() {

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

}
