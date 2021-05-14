package com.yufeng.data.structure.stack.test;

import com.yufeng.data.structure.stack.impl.ArrayStack;

/**
 * 描述:
 *      基于数组实现的栈 ———— 测试类
 * @author yufeng
 * @create 2019-07-10
 */
public class ArrayStackTest {

    public static void main(String[] args) {
        arrayStackTest01();
        arrayStackTest02();
    }


    public static void arrayStackTest01() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(8);
        stack.push(11);

        System.out.println(stack.peek());
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);
    }


    public static void arrayStackTest02() {
        ArrayStack<Integer> stack = new ArrayStack<>(20);
        for (int i = 0; i < 20; i ++) {
            stack.push(i);
        }

        System.out.println(stack);
        System.out.println(stack.getCapacity());
    }
}
