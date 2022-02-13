package com.yufeng.leetcode.stackAndQueue;

import com.yufeng.data.structure.linkedList.LinkedList;
import com.yufeng.leetcode.binaryTree.TreeNode;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 *      leetcode_102: 二叉树的层序遍历
 * @author yufeng
 * @create 2019-09-13
 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 使用LinkedList来作为先入先出的队列
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.addLast(new Pair<>(root, 0));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> front = queue.removeFirst();
            TreeNode node = front.getKey();
            int level = front.getValue();

            if (level == res.size()) {
                res.add(new ArrayList<>());
            }
            res.get(level).add(node.val);
            if (node.left != null) {
                queue.addLast(new Pair<>(node.left, level + 1));
            }
            if (node.right != null) {
                queue.addLast(new Pair<>(node.right, level + 1));
            }
        }
        return res;
    }
}
