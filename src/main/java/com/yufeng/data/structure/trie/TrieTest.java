package com.yufeng.data.structure.trie;

import com.yufeng.data.structure.set.impl.BSTSet;
import com.yufeng.data.structure.set.test.FileOperation;

import java.util.ArrayList;

/**
 * 描述:
 *      字典树测试类
 * @author yufeng
 * @create 2019-08-22
 */
public class TrieTest {

    public static void main(String[] args) {

        compare();

    }

    /**
     *  Trie 和 BSTSet 性能测试
     *  Trie 是更具性能优势的
     *
     *  Pride and Prejudice
     *  Total different words: 6530
     *  BSTSet: 0.09260556 s
     *  Total different words: 6530
     *  Trie: 0.081308912 s
     *
     *  这里由于单词较少，不是很明显。 10W, 100W级别性能就能够凸显出来
     */
    public static void compare() {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {

            long startTime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for (String word : words)
                set.add(word);

            for (String word : words)
                set.contains(word);
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");

            // ------
            startTime = System.nanoTime();
            Trie trie = new Trie();
            for (String word : words)
                trie.add(word);

            for (String word : words)
                trie.contains(word);
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie: " + time + " s");

        }

    }

}
