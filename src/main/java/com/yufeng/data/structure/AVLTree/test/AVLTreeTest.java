package com.yufeng.data.structure.AVLTree.test;

import com.yufeng.data.structure.AVLTree.AVLTree;
import com.yufeng.data.structure.map.impl.BSTMap;
import com.yufeng.data.structure.set.test.FileOperation;

import java.util.ArrayList;

/**
 * 描述:
 *      AVLTree 测试类
 * @author yufeng
 * @create 2019-08-28
 */
public class AVLTreeTest {

    public static void main(String[] args) {
        //avlTreeTest01();
        //avlTreeTest02();
        avlTreeTest03();
    }


    private static void avlTreeTest01() {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();

        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {

            //Test BST
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


    private static void avlTreeTest02() {
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {

            AVLTree<String, Integer> avlTree = new AVLTree<>();
            for (int i = 0; i < words.size(); i ++) {
                if (avlTree.contains(words.get(i))) {
                    avlTree.set(words.get(i), avlTree.get(words.get(i)) + 1);
                } else {
                    avlTree.add(words.get(i), 1);
                }
            }


            System.out.println("Total different words: " + avlTree.getSize());
            System.out.println("Frequency of PRIDE: " + avlTree.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + avlTree.get("prejudice"));

            System.out.println("is BSTMap : " + avlTree.isBST());
            System.out.println("is Balanced : " + avlTree.isBalanced());


            for (String word : words) {
                avlTree.remove(word);
                if (!avlTree.isBST() || !avlTree.isBalanced()) {
                    throw new RuntimeException("ERROR");
                }
            }
        }
        System.out.println();
    }


    private static void avlTreeTest03() {

        AVLTree<Integer, Integer> avlTree = new AVLTree<>();

        avlTree.add(41, 41);
        avlTree.add(22, 22);
        avlTree.add(58, 58);
        avlTree.add(15, 15);
        avlTree.add(33, 33);
        avlTree.add(50, 50);
        avlTree.add(60, 60);
        avlTree.add(13, 13);
        avlTree.add(28, 28);
        avlTree.add(37, 37);
        avlTree.add(42, 42);
        avlTree.add(53, 53);


        System.out.println(avlTree.isBalanced());
    }
}
