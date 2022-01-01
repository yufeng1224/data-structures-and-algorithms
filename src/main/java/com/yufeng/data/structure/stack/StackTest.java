package com.yufeng.data.structure.stack;

import com.yufeng.data.structure.stack.impl.ArrayStack;
import com.yufeng.data.structure.stack.impl.LinkedListStack;

import java.util.Random;

/**
 * @description
 *      栈测试类
 * @author yufeng
 * @create 2019-07-04
 */
public class StackTest {

    public static void main(String[] args) {
        arrayStackTest();
        linkedListStackTest();
        compareStack();
    }

    /**
     * 基于数组实现的栈测试
     */
    public static void arrayStackTest() {
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

    /**
     *  基于链表实现的栈
     */
    public static void linkedListStackTest() {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        for (int i = 0 ; i < 10 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }

    /**
     * 数组栈和链表栈的性能分析比较
     *    1. 数组栈可能会比链表栈慢, 因为linkedList会涉及到new操作, 10W, 100W, 1000W时间可能会有区别
     *    2. 从数据结构的角度来看, 链表栈的性能肯定比数组栈好。但是还需要从JVM创建对象的角度来看, 因为链表栈还涉及到的new对象操作
     *    3. 综合来说, 这两个时间复杂度属于同一复杂度。
     *    4. 执行数据时间比较
     *       4-1 10W级别
     *           ArrayStack, time: 0.027485462 s
     *           LinkedListStack, time: 0.023877306 s
     *       4-2 50W级别
     *           ArrayStack, time: 0.059607895 s
     *           LinkedListStack, time: 0.10881667 s
     */
    public static void compareStack() {
        int opCount = 500000;

        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack, time: " + time1 + " s");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testStack(linkedListStack, opCount);
        System.out.println("LinkedListStack, time: " + time2 + " s");
    }

    /**
     * 测试使用stack 运行opCount个入栈和出栈操作所需要的时间, 单位: 秒
     */
    private static double testStack(Stack<Integer> stack, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i ++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i ++) {
            stack.pop();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

}
