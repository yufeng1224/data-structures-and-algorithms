package com.yufeng.leetcode.aboutLinkedList;

/**
 * @description
 *      节点实体类
 * @author yufeng
 * @create 2019-09-10
 */
public class ListNode {

    public int val;

    ListNode next;

    ListNode(int x) {
        val = x;
    }

    /**
     * 根据n个元素的数组arr创建一个链表
     * 使用arr为参数, 创建另外一个ListNode的构造函数
     */
    public ListNode (int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can not be empty");
        }

        this.val = arr[0];
        ListNode curNode = this;
        for (int i = 1 ; i < arr.length; i ++) {
            curNode.next = new ListNode(arr[i]);
            curNode = curNode.next;
        }
    }

    public ListNode findNode(int x) {
        ListNode curNode = this;
        while (curNode != null) {
            if (curNode.val == x) {
                return curNode;
            }
            curNode = curNode.next;
        }
        return null;
    }

    /**
     * 返回以当前ListNode为头节点的链表信息字符串
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        ListNode curNode = this;
        while (curNode != null) {
            s.append(curNode.val);
            s.append(" -> ");
            curNode = curNode.next;
        }
        s.append("NULL");
        return s.toString();
    }
}
