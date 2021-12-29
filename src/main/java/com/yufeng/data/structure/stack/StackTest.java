package com.yufeng.data.structure.stack;

import com.yufeng.data.structure.stack.impl.ArrayStack;

/**
 * @description
 *      栈测试类
 * @author yufeng
 * @create 2019-07-04
 */
public class StackTest {

    public static void main(String[] args) {
        arrayStackTest01();
    }

    /**
     * 基于数组实现的栈测试
     */
    public static void arrayStackTest01() {
        ArrayStack<Integer> stack = new ArrayStack<>(20);
        for (int i = 0; i < 20; i ++) {
            stack.push(i);
        }
        System.out.println(stack);
        System.out.println("stack.getCapacity(): " + stack.getCapacity());

        System.out.println("stack.peek(): " + stack.peek());
        stack.pop();
        System.out.println(stack);

        stack.push(30);
        System.out.println(stack);
        System.out.println("stack.getSize(): " + stack.getSize());
    }


}
