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
 * @create 2019-09-14
 */
public class ClassicNonRecursivePostorderTraversal {

    private class TagNode {
        TreeNode node;

        boolean isFirst;

        TagNode(TreeNode node) {
            this.node = node;
            this.isFirst = false;
        }
    }

    /**
     * Non-Recursive
     * Using a tag to record whether the node has been visited
     *
     * Time Complexity: O(n), n is the node number in the tree
     * Space Complexity: O(h), h is the height of the tree
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TagNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(new TagNode(cur));
                cur = cur.left;
            }

            TagNode tagNode = stack.pop();
            cur = tagNode.node;
            if (tagNode.isFirst == false) {
                tagNode.isFirst = true;
                stack.push(tagNode);
                cur = cur.right;
            } else {
                res.add(cur.val);
                cur = null;
            }
        }

        return res;
    }

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

    /**
     * Using a pre pointer to record the last visited node
     * Time Complexity: O(n); Space Complexity: O(h)
     */
    public List<Integer> postorderTraversal4(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if ((cur.left == null && cur.right == null) ||
                    (pre != null && pre == cur.left && cur.right == null) ||
                    (pre != null && pre == cur.right)) {
                res.add(cur.val);
                pre = cur;
            } else {
                stack.push(cur);
                if (cur.right != null) {
                    stack.push(cur.right);
                }

                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
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

    /**
     * using a pre pointer to record the last visited node
     * Time Complexity: O(n); Space Complexity: O(h)
     */
    public List<Integer> postorderTraversal6(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;


        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
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
        }

        return res;
    }
}
