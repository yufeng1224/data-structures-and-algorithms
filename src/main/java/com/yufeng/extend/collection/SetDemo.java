package com.yufeng.extend.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @description
 *      set API复习掌握
 * @author yufeng
 * @create 2019-07-16
 */
public class SetDemo {

    /**
     * set基本方法演示(一)
     */
    public static void show01() {
        int[] count = {34, 22, 10, 60, 30, 22};

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < count.length; i++) {
            set.add(count[i]);                                          // 添加元素, 最后一个22不会加入集合中
        }
        System.out.println("HashSet: " + set);                          // [34, 22, 10, 60, 30]
        System.out.println("HashSet size(): " + set.size());            // 5

        TreeSet<Integer> sortedSet = new TreeSet<>(set);
        System.out.println("TreeSet: " + sortedSet);                    // [10, 22, 30, 34, 60] 排序输出
        boolean remove = sortedSet.remove(60);
        System.out.println("sortedSet是否删除元素60: " + remove);
        System.out.println("SortedSet size(): " + sortedSet.size());    // 4
    }

    /**
     * set基本方法演示(二)
     */
    public static void show02() {
        int[] count = {34, 22, 10, 60, 30, 55, 77};

        TreeSet<Integer> sortedSet = new TreeSet<>();
        for (int i = 0; i < count.length; i++) {
            sortedSet.add(count[i]);
        }
        System.out.println("sortedSet: " + sortedSet);                  // [34, 22, 10, 60, 30, 55, 77]

        /** 借助iterator方法遍历集合 */
        Iterator<Integer> iterator = sortedSet.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        /** 加强for循环遍历集合 */
        for (Integer num : sortedSet) {
            System.out.print(num + " ");
        }
        System.out.println();

        boolean contains = sortedSet.contains(88);                      // 判断是否存在元素
        System.out.println("是否包含88? " + contains);                    // false


        int[] subCount = {34, 22, 40};

        TreeSet<Integer> subSortedSet = new TreeSet<>();
        for (int i = 0; i < subCount.length; i++) {
            subSortedSet.add(subCount[i]);
        }
        System.out.println("subSortedSet: " + subSortedSet);            // [34, 22, 40]

        boolean contains2 = sortedSet.containsAll(subSortedSet);
        System.out.println("sortedSet是否包含subSortedSet: " + contains2);  // false

        boolean addAll = sortedSet.addAll(subSortedSet);
        System.out.println("sortedSet是否添加subSortedSet: " + addAll);     // true

        boolean retainAll = sortedSet.retainAll(subSortedSet);
        System.out.println("sortedSet和subSortedSet取交集 " + retainAll);   // true
        System.out.println(sortedSet);                                     // [34, 22, 40]
    }

    /**
     * 1. TreeSet中相关函数掌握
     * 2. 有应用到二分查找法中相关知识点, 比如: ceil、floor等
     */
    public static void show03() {
        int[] count = {11, 22, 33, 44, 55, 66, 77, 88, 99};

        TreeSet<Integer> sortedSet = new TreeSet<>();
        for (int i = 0; i < count.length; i++) {
            sortedSet.add(count[i]);
        }
        System.out.println(sortedSet);

        System.out.println(sortedSet.first());                  // 打印第一个元素: 11
        System.out.println(sortedSet.last());                   // 打印最后一个元素: 99

        /** 返回集合中大于等于当前给定值的最小元素 */
        System.out.println(sortedSet.ceiling(33));           // 33
        System.out.println(sortedSet.ceiling(40));           // 44


        /** 返回集合中小于等于当前给定值的最大元素 */
        System.out.println(sortedSet.floor(33));             // 33
        System.out.println(sortedSet.floor(45));             // 44

        /** 返回集合中大于当前给定值的最小元素 */
        System.out.println(sortedSet.higher(55));            // 66
        /** 返回集合中小于当前给定值的最大元素 */
        System.out.println(sortedSet.lower(55));             // 44
    }

    public static void main(String[] args) {
        show01();
        show02();
        show03();
    }

}
