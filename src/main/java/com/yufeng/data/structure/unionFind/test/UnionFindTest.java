package com.yufeng.data.structure.unionFind.test;

import com.yufeng.data.structure.unionFind.UF;
import com.yufeng.data.structure.unionFind.impl.*;

import java.util.Random;

/**
 * 描述:
 *      并查集性能测试
 * @author yufeng
 * @create 2019-04-28
 */
public class UnionFindTest {

    public static void main(String[] args) {
        unionFindTest01();
        unionFindTest02();
        unionFindTest03();
        unionFindTest04();
        unionFindTest05();
        unionFindTest06();
    }


    /**
     * 1W级别
     *    1. UnionFind1 : 0.136846621 s
     *    2. UnionFind2 : 0.052098946 s
     */
    private static  void unionFindTest01() {
        int size = 10000;
        int m = 10000;

        UnionFind1 uf1 = new UnionFind1(size);
        System.out.println("UnionFind1 : " + testUF(uf1, m) +  " s");

        UnionFind2 uf2 = new UnionFind2(size);
        System.out.println("UnionFind2 : " + testUF(uf2, m) +  " s");
    }


    /**
     * 10W级别(虽然测试不太严谨, 但是性能差距还是比较明显!)
     *    1. UnionFind1 : 0.496574434 s
     *    2. UnionFind2 : 0.003568165 s
     */
    private static  void unionFindTest02() {
        int size = 100000;
        int m = 10000;

        UnionFind1 uf1 = new UnionFind1(size);
        System.out.println("UnionFind1 : " + testUF(uf1, m) +  " s");

        UnionFind2 uf2 = new UnionFind2(size);
        System.out.println("UnionFind2 : " + testUF(uf2, m) +  " s");
    }


    /**
     * 10W级别及10W次操作
     *    1. UnionFind1 : 5.939111996 s
     *
     *    2. UnionFind2 : 11.335737645 s
     *       极端情况下,生成的树退化成一个链表, 导致速度变得很慢!
     *
     *    3. UnionFind3 : 0.020849999 s
     *       可以保证树的深度是比较浅的
     */
    private static  void unionFindTest03() {
        int size = 100000;
        int m = 100000;

        UnionFind1 uf1 = new UnionFind1(size);
        System.out.println("UnionFind1 : " + testUF(uf1, m) +  " s");

        UnionFind2 uf2 = new UnionFind2(size);
        System.out.println("UnionFind2 : " + testUF(uf2, m) +  " s");

        UnionFind3 uf3 = new UnionFind3(size);
        System.out.println("UnionFind3 : " + testUF(uf3, m) +  " s");
    }


    /**
     * 千万级别
     *    1. UnionFind3 : 5.019416229 s
     *    2. UnionFind4 : 4.95352884 s
     */
    private static  void unionFindTest04() {
        int size = 10000000;
        int m = 10000000;

        UnionFind3 uf3 = new UnionFind3(size);
        System.out.println("UnionFind3 : " + testUF(uf3, m) +  " s");

        UnionFind4 uf4 = new UnionFind4(size);
        System.out.println("UnionFind4 : " + testUF(uf4, m) +  " s");
    }


    /**
     * 千万级别
     *    1. UnionFind3 : 4.49127982 s
     *    2. UnionFind4 : 4.531742418 s
     *    3. UnionFind5 : 3.70031393 s
     */
    private static  void unionFindTest05() {
        int size = 10000000;
        int m = 10000000;

        UnionFind5 uf5 = new UnionFind5(size);
        System.out.println("UnionFind5 : " + testUF(uf5, m) +  " s");

        UnionFind3 uf3 = new UnionFind3(size);
        System.out.println("UnionFind3 : " + testUF(uf3, m) +  " s");

        UnionFind4 uf4 = new UnionFind4(size);
        System.out.println("UnionFind4 : " + testUF(uf4, m) +  " s");

    }


    /**
     * 千万级别
     *    1. UnionFind3 : 6.065878791 s
     *
     *    2. UnionFind4 : 5.263593504 s
     *
     *    3. UnionFind5 : 4.066775615 s
     *
     *    4. UnionFind6 : 5.089887368 s
     *       (递归压缩的性能要比非递归压缩的性能差)
     */
    private static  void unionFindTest06() {
        int size = 10000000;
        int m = 10000000;

        UnionFind6 uf6 = new UnionFind6(size);
        System.out.println("UnionFind6 : " + testUF(uf6, m) +  " s");

        UnionFind5 uf5 = new UnionFind5(size);
        System.out.println("UnionFind5 : " + testUF(uf5, m) +  " s");

        UnionFind4 uf4 = new UnionFind4(size);
        System.out.println("UnionFind4 : " + testUF(uf4, m) +  " s");

        UnionFind3 uf3 = new UnionFind3(size);
        System.out.println("UnionFind3 : " + testUF(uf3, m) +  " s");

    }


    /** 测试并查集的性能 */
    private static double testUF(UF uf, int m) {
        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();

        for (int i = 0; i < m; i ++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);             // 合并操作, 进行m次
        }

        for (int i = 0; i < m; i ++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);               // 连接操作, 进行m次
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
