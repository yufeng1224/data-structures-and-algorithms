package com.yufeng.leetcode.stackAndQueue.myStack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description
 *      leetcode-225: 用队列实现栈
 * @author yufeng
 * @create 2019-09-14
 */
public class MyStack2 {

    private Queue<Integer> q;

    private int top;

    public MyStack2() {
        q = new LinkedList<>();
    }

    /**
     * 入栈
     */
    public void push(int x) {
        q.add(x);
        top = x;
    }

    /**
     * 出栈
     */
    public int pop() {
        Queue<Integer> q2 = new LinkedList<>();

        /**
         * 每从q中取出一个元素, 就给top赋值。 top最后指向的是倒数第二个元素, 也就是新的栈顶元素
         */
        while (q.size() > 1) {
            top = q.peek();
            q2.add(q.remove());
        }

        int ret = q.remove();           // 旧的栈顶元素出栈
        q = q2;
        return ret;
    }

    public int top() {
        return top;
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }

}
