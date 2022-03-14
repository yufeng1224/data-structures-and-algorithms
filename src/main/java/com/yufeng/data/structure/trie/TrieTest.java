package com.yufeng.data.structure.trie;

import com.yufeng.data.structure.set.impl.BSTSet;
import com.yufeng.data.structure.set.FileOperation;

import java.util.ArrayList;

/**
 * @description
 *      字典树测试类
 * @author yufeng
 * @create 2019-07-27
 */
public class TrieTest {

    public static void main(String[] args) {
        compare();
    }

    /**
     * Trie和BSTSet性能测试: Trie是更具性能优势的
     *     1. BSTSet: 0.09260556 s
     *     2. Trie: 0.081308912 s
     */
    public static void compare() {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {

            long startTime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for (String word : words) {
                set.add(word);
            }
            for (String word : words) {
                set.contains(word);
            }
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");

            // ------
            startTime = System.nanoTime();
            Trie trie = new Trie();
            for (String word : words) {
                trie.add(word);
            }
            for (String word : words) {
                trie.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie: " + time + " s");
        }
    }
}
