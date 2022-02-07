package com.yufeng.leetcode.aboutLinkedList;

/**
 * @description
 *      leetcode_24
 * @author yufeng
 * @create 2019-09-10
 */
public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode p = dummyHead;
        while (p.next != null && p.next.next != null) {
            ListNode node1 = p.next;
            ListNode node2 = node1.next;

            ListNode next = node2.next;
            node2.next = node1;
            node1.next = next;
            p.next = node2;
            p = node1;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        ListNode head = new ListNode(arr);
        System.out.println(head);

        SwapNodesInPairs s = new SwapNodesInPairs();
        s.swapPairs(head);
        System.out.println(head);
    }
}
