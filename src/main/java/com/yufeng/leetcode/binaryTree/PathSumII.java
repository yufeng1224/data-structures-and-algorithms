package com.yufeng.leetcode.binaryTree;

/**
 * @description
 *      leetcode 437
 * @author yufeng
 * @create 2019-09-06
 */
public class PathSumII {

    /**
     * 1. 在以root为根节点的二叉树中, 寻找和为sum的路径, 返回这样的路径个数
     * 2. 一层层缩小当前的二叉树
     * 3. 寻找的路径是由三部分构成的: 包含当前节点, 以及左右两颗子树中寻找
     * 4. 递归算法中又嵌套了一个递归算法
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        return findPath(root, sum)                      // 当前节点包含在路径中
                + pathSum(root.left, sum)               // 以左节点为根节点, 计算不包含当前节点, 包含左节点的路径
                + pathSum(root.right, sum);             // 以右节点为根节点, 计算不包含当前节点, 包含右节点的路径
    }

    /**
     * 在以node为根节点的二叉树中, 寻找包含node的路径, 和为sum
     * 返回这样的路径个数
     */
    private int findPath(TreeNode node, int num) {
        if (node == null) {
            return 0;
        }

        int res = 0;
        /**
         * 1. 当前已经符合条件, 加一
         * 2. 但是和后面的节点再相加, 可能会又有符合的条件
         * 3. 因为val有负数, 所以还要继续计算下去
         */
        if (node.val == num) {
            res += 1;
        }

        /** 继续寻找节点的左子树和右子树, 判断是否还符合条件 */
        res += findPath(node.left, num - node.val);
        res += findPath(node.right, num - node.val);

        return res;
    }

}
