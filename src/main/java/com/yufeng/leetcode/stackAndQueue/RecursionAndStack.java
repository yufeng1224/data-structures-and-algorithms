package com.yufeng.leetcode.stackAndQueue;

import com.yufeng.leetcode.binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 *      1. 二叉树的前、中、后序遍历(递归实现)
 *      2. 对应leetcode144, 94, 145
 * @author yufeng
 * @create 2019-09-13
 */
public class RecursionAndStack {

    /**
     * 前序遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        preorderTraversal(root, res);
        return res;
    }

    private void preorderTraversal(TreeNode node, List<Integer> list) {
        if (node != null) {
            list.add(node.val);
            preorderTraversal(node.left, list);
            preorderTraversal(node.right, list);
        }
    }

    /**
     * 中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        inorderTraversal(root, res);
        return res;
    }

    private void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node != null) {
            inorderTraversal(node.left, list);
            list.add(node.val);
            inorderTraversal(node.right, list);
        }
    }

    /**
     * 后序遍历
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        postorderTraversal(root, res);
        return res;
    }

    private void postorderTraversal(TreeNode node, List<Integer> list) {
        if (node != null) {
            postorderTraversal(node.left, list);
            postorderTraversal(node.right, list);
            list.add(node.val);
        }
    }
}
