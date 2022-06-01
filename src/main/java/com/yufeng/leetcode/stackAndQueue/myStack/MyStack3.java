package com.yufeng.leetcode.stackAndQueue.myStack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description
 *      leetcode-225: 用队列实现栈
 * @author yufeng
 * @create 2019-09-14
 */
public class MyStack3 {

    private Queue<Integer> q;

    public MyStack3() {
        q = new LinkedList<>();
    }

    /**
     * 入栈: 将队首定义为栈顶。
     *
     */
    public void push(int x) {
        Queue<Integer> q2 = new LinkedList<>();
        q2.add(x);

        while (!q.isEmpty()) {
            q2.add(q.remove());
        }
        q = q2;
    }

    /**
     * 出栈: 当队首定位栈顶后, pop就可以直接从队首取出元素。 时间复杂度为O(1)
     */
    public int pop() {
        return q.remove();
    }

    /**
     * 查看栈顶元素
     */
    public int top() {
        return q.peek();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }

}
