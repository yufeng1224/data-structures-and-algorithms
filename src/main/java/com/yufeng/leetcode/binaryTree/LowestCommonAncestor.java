package com.yufeng.leetcode.binaryTree;

/**
 * @description
 *      leetcode 235
 * @author yufeng
 * @create 2019-09-06
 */
public class LowestCommonAncestor {

    /**
     * 1. 如果p和q分别在根节点的两边, 那么它们的公共祖先肯定是根节点
     * 2. 如果p和q都在根节点的左边, 那么它们的公共祖先肯定是在左子树上
     * 3. 如果p和q都在根节点的右边, 那么它们的公共祖先肯定是在右子树上
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (p == null || q == null) {
            throw new IllegalArgumentException("p or q can not be null.");
        }

        if (root == null) {
            return null;
        }

        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        assert p.val == root.val || q.val == root.val
                || (root.val - p.val) * (root.val - q.val) < 0;

        /**
         * 1. p >= root || q <= root
         * 2. p <= root || q >= root
         * 也就是p和q在当前节点的两边 或者 p和q中有一个节点等于root, 那么直接返回root即可
         * */
        return root;
    }
}
