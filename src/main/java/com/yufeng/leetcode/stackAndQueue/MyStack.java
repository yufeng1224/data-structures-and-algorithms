package com.yufeng.leetcode.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description
 *      leetcode-225: 用队列实现栈
 * @author yufeng
 * @create 2019-09-14
 */
public class MyStack {

    private Queue<Integer> q;

    /** Initialize your data structure here. */
    public MyStack() {
        q = new LinkedList<>();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q.add(x);
    }

    /** Removes the element on top of the stack and returns that element */
    public int pop() {
        Queue<Integer> q2 = new LinkedList<>();
        while (q.size() > 1) {
            q2.add(q.remove());
        }

        // q中剩下的最后一个元素就是"栈顶"元素
        int ret = q.remove();
        q = q2;
        // 返回"栈顶"元素
        return ret;
    }

    public int top() {
        int ret = pop();
        push(ret);
        return ret;
    }
}
