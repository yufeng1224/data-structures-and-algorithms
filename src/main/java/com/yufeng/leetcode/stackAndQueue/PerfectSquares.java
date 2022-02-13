package com.yufeng.leetcode.stackAndQueue;

import javafx.util.Pair;

import java.util.LinkedList;

/**
 * @description
 *    leetcode 279
 * @author yufeng
 * @create 2019-09-13
 */
public class PerfectSquares {

    /**
     * 时间复杂度: O(2^n), 空间复杂度: O(2^n)
     * 该方法会导致Time Limit Exceeded 或者Memory Limit Exceeded
     */
    public int numSquares1(int n) {
        LinkedList<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.addLast(new Pair<>(n, 0));

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> front = queue.removeFirst();
            int num = front.getKey();
            int step = front.getValue();

            if (num == 0) {
                return step;
            }
            for (int i = 1; num - i*i >= 0; i ++) {
                queue.addLast(new Pair<>(num - i*i, step + 1));
            }
        }
        throw new IllegalStateException("No Solution");
    }

    /**
     * 使用visited数组, 记录每一个入队元素
     * 时间复杂度: O(n); 空间复杂度: O(n)
     */
    public int numSquares2(int n) {
        LinkedList<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.addLast(new Pair<>(n, 0));

        boolean[] visited = new boolean[n + 1];
        visited[n] = true;

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> front = queue.removeFirst();
            int num = front.getKey();
            int step = front.getValue();

            if (num == 0) {
                return step;
            }

            for (int i = 1; num - i*i >= 0; i ++) {
                if (!visited[num - i*i]) {
                    queue.addLast(new Pair<>(num - i*i, step + 1));
                    visited[num - i*i] = true;
                }
            }
        }
        throw new IllegalStateException("No Solution");
    }

    /**
     * 进一步优化
     */
    public int numSquares3(int n) {
        if (n == 0) {
            return 0;
        }

        LinkedList<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.addLast(new Pair<>(n, 0));

        boolean[] visited = new boolean[n + 1];
        visited[n] = true;

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> front = queue.removeFirst();
            int num = front.getKey();
            int step = front.getValue();

            if (num == 0) {
                return step;
            }

            for (int i = 1; num - i*i >= 0; i ++) {
                int a = num - i*i;
                if (!visited[a]) {
                    return step + 1;
                }
                queue.addLast(new Pair<>(num - i*i, step + 1));
                visited[num - i*i] = true;
            }
        }

        throw new IllegalStateException("No Solution");
    }

    public static void main(String[] args) {
        PerfectSquares ps = new PerfectSquares();
        System.out.println(ps.numSquares1(12));
        System.out.println(ps.numSquares2(13));
        System.out.println(ps.numSquares3(14));
    }
}
