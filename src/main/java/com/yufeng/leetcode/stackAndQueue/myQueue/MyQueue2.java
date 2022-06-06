package com.yufeng.leetcode.stackAndQueue.myQueue;

import java.util.Stack;

/**
 * @description
 *      用栈实现队列
 * @author yufeng
 * @create
 */
public class MyQueue2 {

    private Stack<Integer> stack;

    int front;                      // 维护队首元素的变量

    public MyQueue2() {
        stack = new Stack<>();
    }

    /**
     * 定义栈顶是队尾, 入队时间复杂度是O(1)
     */
    public void push(int x) {
        if (empty()) {
            front = x;
        }
        stack.push(x);
    }

    public int pop() {
        Stack<Integer> stack2 = new Stack<>();

        // 把 stack 的元素暂存进 stack2
        while (stack.size() > 1) {
            front = stack.peek();
            stack2.push(stack.pop());
        }

        int ret = stack.pop();
        while (!stack2.isEmpty()) {
            stack.push(stack2.pop());
        }
        return ret;
    }

    /**
     * 查看队首元素, 时间复杂度是O(1)
     */
    public int peek() {
        return front;
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}
