package com.yufeng.data.structure.binarySearchTree.test;

import com.yufeng.data.structure.binarySearchTree.BST;

import java.util.ArrayList;
import java.util.Random;

/**
 * 描述:
 *      二分搜索树测试类
 * @author yufeng
 * @create 2019-07-22
 */
public class BSTTest {

    public static void main(String[] args) {
        bstTest01();

        bstTest02();

        bstTest03();
    }


    private static void bstTest01() {
        BST<Integer> bst = new BST<>();
        int[] nums = {10, 5, 4, 8, 15, 12, 16};

        for (int num : nums) {
            bst.add(num);
        }

        // ***********************//
        //          10            //
        //         /   \          //
        //       5      15        //
        //     /   \   /   \      //
        //    4     8 12   16     //
        // ********************** //

        System.out.println(bst.toString());
        System.out.println("bst.contains(5) : " + bst.contains(5));
        System.out.println("bst.contains(7) : " + bst.containsNR(7));

        // 前序遍历输出
        System.out.println("前序遍历(递归实现)");
        bst.preOrder();                             // 10 5 4 8 15 12 16
        System.out.println();

        System.out.println("前序遍历(非递归实现)");
        bst.preOrderNR();
        System.out.println();

        // 中序遍历输出
        System.out.println("中序遍历(递归实现)");
        bst.inOrder();                              // 4 5 8 10 12 15 16
        System.out.println();

        System.out.println("中序遍历(非递归实现)");
        bst.inOrderNR();
        System.out.println();


        // 后序遍历: 4 8 5 12 16 15 10
        System.out.println("后序遍历(递归实现)");
        bst.postOrder();                            // 4 8 5 12 16 15 10
        System.out.println();

        System.out.println("后序遍历(非递归实现)");
        bst.postOrderNR();
        System.out.println();

        System.out.println("层序遍历(广度优先遍历)");
        bst.levelOrder();                           // 10 5 15 4 8 12 16
        System.out.println();
    }


    private static void bstTest02() {
        // 最小值测试
        BST<Integer> bst = new BST<>();
        Random random = new Random();

        int n = 1000;
        for (int i = 0; i < n ; i ++) {
            bst.add(random.nextInt(10000));
        }

        int maxDepth = bst.maxDepth();
        System.out.println("递归计算树的高度: " + maxDepth);

        int maxDepthLevelOrder = bst.maxDepthLevelOrder();
        System.out.println("非递归计算树的高度: " + maxDepthLevelOrder);

        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty()) {
            nums.add(bst.removeMin());
        }
        System.out.println(nums);

        for (int i = 1 ; i < nums.size() ; i ++) {
            if (nums.get(i-1) > nums.get(i)) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("removeMin test completed");
    }


    private static void bstTest03() {
        BST<Integer> bst = new BST<>();
        int[] nums = {10, 5, 4, 8, 15, 12, 16};

        for (int num : nums) {
            bst.addNode(num);
        }


    }

}
