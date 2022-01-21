package com.yufeng.leetcode.binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 *     leetcode 257
 * @author yufeng
 * @create 2019-09-06
 */
public class BinaryTreePaths {

    /**
     * 1. 获得当前树的所有路径
     * 2. 问题可以归纳为: 当前节点 -> 它的左子树路径, 当前节点 -> 它的右子树路径
     */
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        /** 递归终止条件: 当前节点为叶子节点 */
        if (root.left == null && root.right == null) {
            res.add(Integer.toString(root.val));
            return res;
        }

        List<String> leftPaths = binaryTreePaths(root.left);
        for (String s: leftPaths) {
            StringBuilder sb = new StringBuilder(Integer.toString(root.val));
            sb.append("->");
            sb.append(s);
            res.add(sb.toString());
        }

        List<String> rightPaths = binaryTreePaths(root.right);
        for (String s: rightPaths) {
            StringBuilder sb = new StringBuilder(Integer.toString(root.val));
            sb.append("->");
            sb.append(s);
            res.add(sb.toString());
        }

        return res;
    }

    public List<String> binaryTreePaths2(TreeNode root) {
        ArrayList<String> res = new ArrayList();

        /** 递归终止条件: 当前节点为叶子节点 */
        if (root.left == null && root.right == null) {
            res.add(Integer.toString(root.val));                        // 将叶子节点放入到数组中并返回
            return res;
        }

        if (root.left != null) {
            List<String> left = binaryTreePaths2(root.left);            // 计算左子树的所有路径
            /** 取出左子树中的所有路径, 并与当前节点组成新的路径 */
            for (int i = 0; i < left.size(); i ++) {
                StringBuilder sb = new StringBuilder();
                sb.append(root.val).append("->").append(left.get(i));
                res.add(sb.toString());                                 // 存入当前数组中
            }
        }

        if (root.right != null) {
            List<String> right = binaryTreePaths2(root.right);          // 计算右子树的所有路径
            /** 取出左子树中的所有路径, 并与当前节点组成新的路径 */
            for (int i = 0; i < right.size(); i ++) {
                StringBuilder sb = new StringBuilder();
                sb.append(root.val).append("->").append(right.get(i));
                res.add(sb.toString());                                 // 存入当前数组中
            }
        }
        /** 最终返回当前节点所有的路径 */
        return res;
    }
}
