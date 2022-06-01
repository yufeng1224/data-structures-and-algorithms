package com.yufeng.leetcode.stackAndQueue.myStack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description
 *      leetcode-225: 用队列实现栈
 * @author yufeng
 * @create 2019-09-14
 */
public class MyStack1 {

    private Queue<Integer> q;

    public MyStack1() {
        q = new LinkedList<>();
    }

    /**
     * 判断队列是否为空
     */
    public boolean empty() {
        return q.isEmpty();
    }

    /**
     * 入栈: 只能使用队列的 push to back 性质, 也就是向队尾添加元素
     */
    public void push(int x) {
        q.add(x);
    }

    /**
     * 出栈: 只能使用队列的 pop from front 性质, 也就是从队首移除元素
     */
    public int pop() {
        Queue<Integer> q2 = new LinkedList<>();
        while (q.size() > 1) {
            q2.add(q.remove());
        }

        int ret = q.remove();           // q中剩下的最后一个元素就是"栈顶"元素
        q = q2;
        return ret;                     // 返回"栈顶"元素
    }

    public int top() {
        int ret = pop();                // 先出栈
        push(ret);                      // 用变量保存该元素后, 再将元素入栈
        return ret;
    }
}
