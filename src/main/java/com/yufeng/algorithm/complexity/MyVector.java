package com.yufeng.algorithm.complexity;

/**
 * @description
 *      自定义数组
 * @author yufeng
 * @create 2019-09-01
 */
public class MyVector<Item> {

    private Item[] data;

    private int size;           // 存储数组中的元素个数

    private int capacity;       // 存储数组中可以容纳的最大的元素个数

    public MyVector() {
        data = (Item[])new Object[100];
        size = 0;
        capacity = 100;
    }

    /**
     * 平均复杂度: O(1)
     */
    public void push(Item e) {
        if (size == capacity) {
            resize(2 * capacity);
        }
        data[size ++] = e;
    }

    /**
     * 平均复杂度: O(1)
     */
    public Item pop() {
        if (size <= 0) {
            throw new IllegalArgumentException("can not pop back for empty vector.");
        }
        size --;

        if (size == capacity / 4) {
            resize(capacity / 2);
        }
        return data[size];
    }

    /**
     * 复杂度: O(n)
     */
    private void resize(int newCapacity) {
        assert newCapacity >= size;
        Item[] newData = (Item[])new Object[newCapacity];
        for (int i = 0; i < size; i ++) {
            newData[i] = data[i];
        }
        data = newData;
        capacity = newCapacity;
    }

    /**
     * Java语言由于JVM内部机制的因素, 测量的性能时间有可能跳跃不稳定的
     */
    public static void main(String[] args) {
        for (int i = 10; i <= 26; i ++) {
            int n = (int)Math.pow(2, i);
            long startTime = System.currentTimeMillis();
            MyVector<Integer> vec = new MyVector<>();
            for (int num = 0; num < n; num ++) {
                vec.push(num);
            }

            for (int num = 0; num < n; num ++) {
                vec.pop();
            }
            long endTime = System.currentTimeMillis();

            System.out.print(2 * n + " operation: \t");
            System.out.println((endTime - startTime) + " ms");
        }
    }
}
