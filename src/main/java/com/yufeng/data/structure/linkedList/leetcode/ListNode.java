package com.yufeng.data.structure.linkedList.leetcode;


public class ListNode {

    public int val;

    public ListNode next;


    public ListNode(int x) {
        val = x;
    }


    /**
     * 链表节点的构造函数
     * 使用 arr 为参数, 创建一个链表, 当前的 ListNode 为链表头节点
     */
    public ListNode(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can not be empty");
        }
        this.val = arr[0];
        ListNode cur = this;
        for (int i = 1 ; i < arr.length ; i ++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
