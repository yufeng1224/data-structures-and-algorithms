package com.yufeng.leetcode.stackAndQueue.myQueue;

import java.util.Stack;

/**
 * @description
 *      用栈实现队列
 * @author yufeng
 * @create
 */
public class MyQueue1 {

    private Stack<Integer> stack;

    public MyQueue1() {
        stack = new Stack<>();
    }

    /**
     * 定义栈顶就是队首, 则入队操作时间复杂度是O(n)
     */
    public void push(int x) {
        Stack<Integer> stack2 = new Stack<>();

        // 把 stack 的元素暂存进 stack2
        while (!stack.isEmpty()) {
            stack2.push(stack.pop());
        }

        // 将新元素x插入到stack底部
        stack.push(x);

        // 把 stack2 中的元素放回 stack中
        while (!stack2.isEmpty()) {
            stack.push(stack2.pop());
        }
    }

    /**
     * 出队, 时间复杂度是O(1)
     */
    public int pop() {
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }

    public boolean empty() {
        return stack.isEmpty();
    }

}
