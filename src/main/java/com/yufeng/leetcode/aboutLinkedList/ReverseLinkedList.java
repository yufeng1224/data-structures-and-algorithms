package com.yufeng.leetcode.aboutLinkedList;

/**
 * @description
 *      leetcode_206
 * @author yufeng
 * @create 2019-09-10
 */
public class ReverseLinkedList {

    public ListNode reverseList1(ListNode head) {
        ListNode pre = null;

        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    /**
     * 1. 递归方式反转链表
     * 2. 注意: 递归是占用空间的, 占用空间的大小和递归深度成正比
     * 3. 该解法运用到了双向指针的概念
     */
    public ListNode reverseList2(ListNode head) {
        // 递归终止条件
        if (head == null || head.next == null) {
            return head;
        }

        ListNode rhead = reverseList2(head.next);
        /**
         * 1. 后一个节点指回了前一个节点
         * 2. 类似于双向链表中的head.next.prev = head
         */
        head.next.next = head;
        head.next = null;

        return rhead;
    }

    public static void main(String[] args) {
        ReverseLinkedList r = new ReverseLinkedList();

        int[] nums = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        ListNode rHead1 = r.reverseList1(head);
        System.out.println(rHead1);

        int[] nums2 = {1, 3, 5, 7, 9};
        ListNode head2 = new ListNode(nums2);
        System.out.println(head2);
        ListNode rHead2 = r.reverseList2(head2);
        System.out.println(rHead2);
    }

}
