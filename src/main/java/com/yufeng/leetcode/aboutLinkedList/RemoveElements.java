package com.yufeng.leetcode.aboutLinkedList;

/**
 * @description
 *      leetcode_203
 * @author yufeng
 * @create 2019-09-10
 */
public class RemoveElements {

    public ListNode removeElements1(ListNode head, int val) {
        // 需要对头节点进行特殊处理
        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) {
            return head;
        }

        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                ListNode delNode = cur.next;
                cur.next = delNode.next;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }

    public ListNode removeElements2(ListNode head, int val) {
        // 创建虚拟头节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode cur = dummyHead;
        while (cur.next != null) {
            if (cur.next.val == val) {
                ListNode delNode = cur.next;
                cur.next = delNode.next;
            } else {
                cur = cur.next;
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 6, 3, 4, 5, 6};
        int val = 6;

        ListNode head = new ListNode(arr);
        System.out.println(head);

        RemoveElements r = new RemoveElements();
        r.removeElements1(head, val);
        System.out.println(head);

        int[] arr2 = {1, 2, 6, 3, 4, 2, 6};
        int val2 = 2;

        ListNode head2 = new ListNode(arr2);
        System.out.println(head2);
        r.removeElements2(head2, val2);
        System.out.println(head2);
    }

}
