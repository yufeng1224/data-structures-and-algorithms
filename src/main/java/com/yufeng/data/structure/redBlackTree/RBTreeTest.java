package com.yufeng.data.structure.redBlackTree;

import com.yufeng.data.structure.AVLTree.AVLTree;
import com.yufeng.data.structure.map.impl.BSTMap;
import com.yufeng.data.structure.set.FileOperation;

import java.util.ArrayList;

/**
 * @description
 *      AVLTree 测试类
 * @author yufeng
 * @create 2019-08-03
 */
public class RBTreeTest {

    public static void main(String[] args) {
        avlTreeTest01();
    }

    /**
     * BSTMap和AVLTree性能比较
     */
    private static void avlTreeTest01() {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {

            long startTime = System.nanoTime();

            BSTMap<String, Integer> bst = new BSTMap<>();
            for (int i = 0; i < words.size(); i ++) {
                String word = words.get(i);
                if (bst.contains(word)) {
                    bst.set(word, bst.get(word) + 1);
                } else {
                    bst.add(word, 1);
                }
            }

            for (String word : words) {
                bst.contains(word);
            }

            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST: " + time + " s");

            startTime = System.nanoTime();
            RBTree<String, Integer> rbTree = new RBTree<>();
            for (String word : words) {
                if (rbTree.contains(word)) {
                    rbTree.set(word, rbTree.get(word) + 1);
                } else {
                    rbTree.add(word, 1);
                }
            }

            for (String word : words) {
                rbTree.contains(word);
            }

            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("RBTree: " + time + " s");


            startTime = System.nanoTime();
            AVLTree<String, Integer> avlTree = new AVLTree<>();
            for (String word : words) {
                if (avlTree.contains(word)) {
                    avlTree.set(word, avlTree.get(word) + 1);
                } else {
                    avlTree.add(word, 1);
                }
            }

            for (String word : words) {
                avlTree.contains(word);
            }

            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVLTree: " + time + " s");
        }
    }
}
