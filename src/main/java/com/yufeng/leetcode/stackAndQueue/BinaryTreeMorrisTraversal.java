package com.yufeng.leetcode.stackAndQueue;

import com.yufeng.leetcode.binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 *      
 * @author yufeng
 * @create 2019-09-14
 */
public class BinaryTreeMorrisTraversal {

    /**
     * PreOrder Morris Traversal
     * Time Complexity: O(n), n is the node number in the tree
     * Space Complexity: O(h)
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    res.add(cur.val);
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }

        return res;
    }

    /**
     * InOrder Morris Traversal
     * Time Complexity: O(n), n is the node number in the tree
     * Space Complexity: O(1)
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    prev.right = null;
                    res.add(cur.val);                           // 就一行代码有区别!
                    cur = cur.right;
                }
            }
        }

        return res;
    }

    /**
     * PostOrder Morris Traversal
     * Time Complexity: O(n), n is the node number in the tree
     * Space Complexity: O(h)
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                TreeNode prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    prev.right = null;
                    reverseTraversal(cur.left, res);
                    cur = cur.right;
                }
            }
        }

        return res;
    }

    private void reverseTraversal(TreeNode node, ArrayList<Integer> res) {
        int start = res.size();
        while (node != null) {
            res.add(node.val);
            node = node.right;
        }

        int i = start, j = res.size() - 1;
        while (i < j) {
            Integer t = res.get(i);
            res.set(i, res.get(j));
            res.set(j, t);
            i ++;
            j --;
        }
    }

}






















