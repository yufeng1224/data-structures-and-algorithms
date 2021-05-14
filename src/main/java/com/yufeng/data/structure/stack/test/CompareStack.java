package com.yufeng.data.structure.stack.test;

import com.yufeng.data.structure.stack.IStack;
import com.yufeng.data.structure.stack.impl.ArrayStack;
import com.yufeng.data.structure.stack.impl.LinkedListStack;

import java.util.Random;

/**
 * 描述:
 *      数组栈和链表栈的性能分析比较
 *         1. 数组栈可能会比链表栈慢, 因为 linkedList 会涉及到 new 操作， 10W, 100W, 1000W
 *            时间可能会有区别。
 *
 *         2. 从数据结构的角度来看, 链表栈的性能肯定比数组栈好， 但是还需要从JVM创建对象的角度来看,
 *             因为链表栈还涉及到的new对象操作!
 *
 *         3. 综合来说, 这两个时间复杂度属于同一复杂度。
 *
 *
 * 执行数据时间比较
 *      10W级别
 *          ArrayStack, time: 0.027485462 s
 *          LinkedListStack, time: 0.023877306 s
 *
 *      50W级别
 *          ArrayStack, time: 0.059607895 s
 *          LinkedListStack, time: 0.10881667 s
 *
 *      从理论上分析复杂度差异不大! 都是O(1)级别的
 *
 * @author yufeng
 * @create 2019-07-10
 */
public class CompareStack {

    // 测试使用queue 运行 opCount 个 enqueue 和 dequeue 操作所需要的时间, 单位: 秒
    private static double testStack(IStack<Integer> stack, int opCount) {

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


    public static void main(String[] args) {

        int opCount = 500000;

        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack, time: " + time1 + " s");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testStack(linkedListStack, opCount);
        System.out.println("LinkedListStack, time: " + time2 + " s");

        // 其实这个时间比较很复杂,  因为 LinkedListStack 中包含更多的new 操作。
    }

}
