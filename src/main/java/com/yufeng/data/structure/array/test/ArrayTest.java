package com.yufeng.data.structure.array.test;

import com.yufeng.data.structure.array.Array;

/**
 * 描述:
 *     自定义数组测试类
 * @author yufeng
 * @create 2019-07-01
 */
public class ArrayTest {

    public static void main(String[] args) {
        arrayTest01();
        arrayTest02();
    }

    /**
     * arrayTest01
     */
    public static void arrayTest01() {
        Array<Integer> array = new Array<>(20);
        for (int i = 0; i < 10; i ++) {
            array.addLast(i);
        }
        System.out.println(array);

        array.add(1, 100);
        System.out.println(array);

        array.addFirst(-1);
        System.out.println(array);

        array.remove(2);
        System.out.println(array);

        array.removeElement(4);
        System.out.println(array);

        array.removeFirst();
        System.out.println(array);
    }

    /**
     * arrayTest02
     */
    public static void arrayTest02() {
        Array<Integer> array = new Array<>(20);
        for (int i = 0; i < 10; i ++) {
            array.addLast(i);
        }
        System.out.println(array);

        array.addFirst(8);
        array.addLast(10);
        array.addLast(7);
        System.out.println(array);

        int[] indexArr = array.findAll(7);
        for (int index = 0; index < indexArr.length; index ++) {
            System.out.println("包含元素7的index: " + indexArr[index]);
        }

        array.removeAllElement(8);
        System.out.println(array);
    }
}
