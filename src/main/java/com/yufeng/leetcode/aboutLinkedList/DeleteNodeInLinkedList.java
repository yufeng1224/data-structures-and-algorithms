package com.yufeng.leetcode.aboutLinkedList;

/**
 * @description
 *      leetcode_237
 * @author yufeng
 * @create 2019-09-10
 */
public class DeleteNodeInLinkedList {

    public void deleteNode(ListNode node) {
        if (node == null || node.next == null) {
            throw new IllegalArgumentException("node should be valid and can not be the tail node");
        }

        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        ListNode head = new ListNode(arr);
        System.out.println(head);

        DeleteNodeInLinkedList d = new DeleteNodeInLinkedList();
        ListNode node2 = head.findNode(2);
        d.deleteNode(node2);
        System.out.println(head);
    }
}
