package com.yufeng.data.structure.hashTable.test;

import com.yufeng.data.structure.hashTable.HashTable;
import com.yufeng.data.structure.set.FileOperation;

import java.util.ArrayList;

/**
 * @description
 *      HashTable测试
 * @author yufeng
 * @create 2019-08-08
 */
public class HashTableTest {

    private void hashtableTest01() {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {

            //Test HashTable
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
            System.out.println("HashTable: " + time + " s");
        }
    }

    public static void main(String[] args) {
        HashTableTest ht = new HashTableTest();
        ht.hashtableTest01();
    }
}
