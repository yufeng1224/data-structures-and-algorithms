package com.yufeng.data.structure.heap.leetcode;

import java.util.*;

/**
 * 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按任意顺序返回答案。
 *
 * 示例 1:
 *    输入: nums = [1,1,1,2,2,3], k = 2
 *    输出: [1,2]
 *
 *
 * 示例 2:
 *    输入: nums = [1], k = 1
 *    输出: [1]
 *
 */
public class LeetCode_347 {

    // 如果涉及到Java 标准库中所涉及到的类, 有一个解决方案 Comparator
//     private class Freq implements Comparable<Freq>{
//         public int e, freq;

//         public Freq(int e, int freq) {
//             this.e = e;
//             this.freq = freq;
//         }

//         @Override
//         public int compareTo(Freq another) {
//             if (this.freq < another.freq)
//                 return -1;
//             else if (this.freq > another.freq)
//                 return 1;
//             else
//                 return 0;
//         }

//     }


    /**
     * 知识点延伸:
     *    1. Java中默认字符串的比较是按字典序比较的;
     *    2. 如果有一个自己定制的比较方案, 比如按字符串长度来比较， 此时是不能修改Java内置的String的比较方式;
     *    3. 那么可以定义一个属于自己的比较器, 然后传给 PriorityQueue 就好了;
     */
//     private class FreqComparator implements Comparator<Freq> {

//         @Override
//         public int compare(Freq a, Freq b) {
//             return a.freq - b.freq;
//         }

//     }


    public static List<Integer> topKFrequent(int[] nums, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // 创建一个优先队列(此处还可以使用 lambda 表达式)
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);         // 变量捕获
            }
        });

        for (int key : map.keySet()) {
            // 先将k个元素放入优先队列, 放满为止
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())){      // 新的元素频次比堆顶的元素频次高
                pq.remove();                                    // 将堆顶那个频次最低的元素出队
                pq.add(key);
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove());
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,2,3};
        int k = 2;
        System.out.println(topKFrequent(nums, k));
    }
}
