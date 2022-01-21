package com.yufeng.leetcode.binaryTree;

/**
 * @description
 *      leetcode 226
 * @author yufeng
 * @create 2019-09-06
 */
public class InvertBinaryTree {

    /**
     * 时间复杂度: O(n), n为树中节点个数
     * 空间复杂度: O(h), h为树的高度
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }
}
