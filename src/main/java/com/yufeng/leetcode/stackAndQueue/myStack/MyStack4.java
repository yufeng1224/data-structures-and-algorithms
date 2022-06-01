package com.yufeng.leetcode.stackAndQueue.myStack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description
 *      leetcode-225: 用队列实现栈
 * @author yufeng
 * @create 2019-09-14
 */
public class MyStack4 {

    private Queue<Integer> q;

    public MyStack4() {
        q = new LinkedList<>();
    }

    /**
     * 1. 入栈: 将队首定义为栈顶, 并使用一个队列解决问题
     * 2. 将x调整到q的队首, 比如我们的队列中, 元素是{1, 2, 3}, 对于栈来说, 添加元素后, 我们
     *    希望得到{4, 1, 2, 3}
     * 3. 但是因为q是队列, 入队之后得到的数据是 {1, 2, 3, 4}, 所以需要执行3次出队再入队操作
     *    3-1 出队1再入队1, 得到: {2, 3, 4, 1}
     *    3-2 出队2再入队2, 得到: {3, 4, 1, 2}
     *    3-3 出队3再入队3, 得到: {4, 1, 2, 3}
     */
    public void push(int x) {
        q.add(x);

        // 执行 n-1 次出队再入队的操作
        for (int i = 1; i < q.size(); i++) {
            q.add(q.remove());
        }
    }

    /**
     * 出栈
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
