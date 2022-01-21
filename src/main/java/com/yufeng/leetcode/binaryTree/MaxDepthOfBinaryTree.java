package com.yufeng.leetcode.binaryTree;

/**
 * @description
 *      leetcode 104
 * @author yufeng
 * @create 2019-09-06
 */
public class MaxDepthOfBinaryTree {

    /**
     * 时间复杂度: O(n), n是树中的节点个数
     * 空间复杂度: O(h), h是树的高度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

}
