package com.yufeng.data.structure.stack.test;

import com.yufeng.data.structure.stack.impl.LinkedListStack;


/**
 * 描述:
 *      基于链表实现的栈 ———— 测试类
 * @author yufeng
 * @create 2019-07-10
 */
public class LinkedListStackTest {

    public static void main(String[] args) {
        linkedListStackTest01();
    }


    public static void linkedListStackTest01() {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        for (int i = 0 ; i < 10 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }
}
