package com.yufeng.leetcode.stackAndQueue;

import com.yufeng.leetcode.binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @description
 *      二叉树后序遍历非递归实现
 * @author yufeng
 * @create 2019-09-13
 */
public class ClassicNonRecursivePostorderTraversal {

    /**
     * using two stacks, revers preorder traversal!
     * time complexity: O(n); space complexity: O(n)
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> output = new Stack<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            output.push(cur.val);

            if (cur.left != null) {
                stack.push(cur.left);
            }

            if (cur.right != null) {
                stack.push(cur.right);
            }
        }

        while (!output.isEmpty()) {
            res.add(output.pop());
        }

        return res;
    }

    public List<Integer> postorderTraversal3(TreeNode root) {
        ArrayList<Integer> res= new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        LinkedList<TreeNode> output = new LinkedList<>();

        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                output.push(cur);
                cur = cur.right;
            } else {
                cur = stack.pop();
                cur = cur.left;
            }
        }

        while (!output.isEmpty()) {
            res.add(output.pop().val);
        }

        return res;
    }

    public List<Integer> postorderTraversal5(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            if (cur.right == null || pre == cur.right) {
                res.add(cur.val);
                pre = cur;
                cur = null;
            } else {
                stack.push(cur);
                cur = cur.right;
            }
        }

        return res;
    }

}
