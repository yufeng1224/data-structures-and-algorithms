package com.yufeng.data.structure.linkedList.leetcode;


/**
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回新的头节点。
 *
 * 示例 1：
 *      输入：head = [1,2,6,3,4,5,6], val = 6
 *      输出：[1,2,3,4,5]
 *
 * 示例 2：
 *      输入：head = [], val = 1
 *      输出：[]
 *
 * 示例 3：
 *      输入：head = [7,7,7,7], val = 7
 *      输出：[]
 *  
 * 提示：
 *      列表中的节点在范围 [0, 104] 内
 *      1 <= Node.val <= 50
 *      0 <= k <= 50
 *
 */
public class LeetCode_203 {

    /**
     * 采用虚拟头结点, 循环遍历删除
     *
     * 执行结果：通过 显示详情
     *    执行用时：1 ms, 在所有 Java 提交中击败了99.83%的用户
     *    内存消耗：39.4 MB, 在所有 Java 提交中击败了51.47%的用户
     */
    public static ListNode removeElements(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;

        while( prev.next != null) {
            if (prev.next.val == val)
                prev.next = prev.next.next;
            else
                prev = prev.next;
        }
        return dummyHead.next;
    }


    /**
     * 采用递归方式删除
     *
     * 执行结果：通过  显示详情
     *    执行用时：1 ms, 在所有 Java 提交中击败了99.83%的用户
     *    内存消耗：39.9 MB, 在所有 Java 提交中击败了5.80%的用户
     */
    public static ListNode removeElementsByRecursive(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElementsByRecursive(head.next, val);
        return head.val == val ? head.next : head;
    }


    /**
     * 不采用虚拟头结点的方式
     *
     * 执行结果：通过 显示详情
     *    执行用时：1 ms, 在所有 Java 提交中击败了99.83%的用户
     *    内存消耗：39.4 MB, 在所有 Java 提交中击败了65.40%的用户
     */
    public static ListNode removeElementsNoDummyHead(ListNode head, int val) {

        // 1. 如果当前链表为空, 则直接返回
        if (head == null) {
            return null;
        }

        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null){          // 这样子要写两遍
            return null;
        }

        // 循环遍历, 剔除头结点为6的情况
        ListNode prevDelHead = head;
        while (prevDelHead.next != null) {
            if (prevDelHead.next.val == val) {
                prevDelHead.next = prevDelHead.next.next;
            } else
                prevDelHead = prevDelHead.next;
        }

        return head;
    }


    /**
     * 递归方法的调试, 多增加一些打印输出
     *
     * @param
     */
    public static ListNode removeElements(ListNode head, int val, int depth) {
        String depthString = generateDepthString(depth);
        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in" + head);

        if (head == null){
            System.out.println(depthString);
            System.out.println("Return: " + head);
            return head;
        }

        ListNode res = removeElements(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove: " + val + ": " + res);

        ListNode ret;
        if (head.val == val) {
            ret = res;
        } else {
            head.next = res;
            ret = head;
        }
        System.out.print(depthString);
        System.out.println("Return: " + ret);
        return ret;
    }


    private static String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i ++) {
            res.append("--");
        }
        return res.toString();
    }


    public static void main(String[] args) {
        int[] arr = {1,2,6,3,4,5,6,7,3};
        ListNode listNode = new ListNode(arr);
        removeElements(listNode, 6);
        removeElementsByRecursive(listNode, 6);
        removeElementsNoDummyHead(listNode, 6);
        removeElements(listNode, 6, 0);
    }
}
