package com.yufeng.data.structure.hashTable.test;

import com.yufeng.data.structure.hashTable.HashTable;
import com.yufeng.data.structure.set.test.FileOperation;

import java.util.ArrayList;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2021-05-11
 */
public class HashTableTest {

    public static void main(String[] args) {
        hashtableTest01();
    }


    private static void hashtableTest01() {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();

        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {

            //Test BST
            long startTime = System.nanoTime();

            HashTable<String, Integer> hashTable = new HashTable<>();
            for (String word : words) {
                if (hashTable.contains(word)) {
                    hashTable.set(word, hashTable.get(word) + 1);
                } else {
                    hashTable.add(word, 1);
                }
            }

            for (int i = 0; i < words.size(); i ++) {
                hashTable.contains(words.get(i));
            }

            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 10000;
            System.out.println("BST: " + time + " s");
        }
    }



}
