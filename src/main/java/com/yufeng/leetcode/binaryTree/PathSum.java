package com.yufeng.leetcode.binaryTree;

/**
 * @description
 *      leetcode 112
 * @author yufeng
 * @create 2019-09-06
 */
public class PathSum {

    /**
     * 时间复杂度: O(n), n为树的节点个数
     * 空间复杂度: O(h), h为树的高度
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return sum == root.val;
        }

        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);
    }

}
