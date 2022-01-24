package com.yufeng.data.structure.map.test;

import com.yufeng.data.structure.map.Map;
import com.yufeng.data.structure.map.impl.BSTMap;
import com.yufeng.data.structure.map.impl.LinkedListMap;
import com.yufeng.data.structure.set.FileOperation;

import java.util.ArrayList;

/**
 * 描述:
 *      LinkedListMap 和 BSTMap 性能比较
 * @author yufeng
 * @create 2019-08-15
 */
public class CompareMap {

    public static void main(String[] args) {

        String filename = "pride-and-prejudice.txt";

        BSTMap<String, Integer> bstMap = new BSTMap<>();
        double time1 = testMap(bstMap, filename);
        System.out.println("BST Map: " + time1 + " s");

        System.out.println();

        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        double time2 = testMap(linkedListMap, filename);
        System.out.println("Linked List Map: " + time2 + " s");

    }


    /**
     * BST Map: 0.218165727 s
     * Linked List Map: 11.268514581 s
     */
    private static double testMap(Map<String, Integer> map, String filename) {

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
}
