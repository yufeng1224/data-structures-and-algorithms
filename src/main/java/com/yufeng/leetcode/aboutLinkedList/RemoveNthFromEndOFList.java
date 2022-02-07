package com.yufeng.leetcode.aboutLinkedList;

/**
 * @description
 *      leetcode_19
 * @author yufeng
 * @create 2019-09-10
 */
public class RemoveNthFromEndOFList {

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        int length = 0;
        for (ListNode cur = dummyHead.next; cur != null; cur = cur.next) {
            length ++;
        }

        int k = length - n;

        ListNode cur = dummyHead.next;
        for (int i = 0; i < k; i ++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummyHead.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode p = dummyHead;
        ListNode q = dummyHead;

        for (int i = 0; i < n + 1; i ++) {
            q = q.next;
        }

        while (q != null) {
            p = p.next;
            q = q.next;
        }
        p.next = p.next.next;
        return dummyHead.next.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(arr);
        System.out.println(head);

        RemoveNthFromEndOFList r = new RemoveNthFromEndOFList();
        r.removeNthFromEnd1(head, 2);
        System.out.println(head);

        r.removeNthFromEnd2(head, 2);
        System.out.println(head);
    }
}
