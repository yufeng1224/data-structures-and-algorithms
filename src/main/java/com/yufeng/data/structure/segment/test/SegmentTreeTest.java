package com.yufeng.data.structure.segment.test;

import com.yufeng.data.structure.segment.SegmentTree;

/**
 * 描述:
 *      线段树测试类
 * @author yufeng
 * @create 2019-08-21
 */
public class SegmentTreeTest {

    public static void main(String[] args) {
        test1();
        test2();
    }


    public static void test1() {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segmentTree.toString());
    }


    public static void test2() {
        Integer[] nums = {-2, 0, 3, -5, 2, -1, 10};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segmentTree.query(0, 2));
    }

}
