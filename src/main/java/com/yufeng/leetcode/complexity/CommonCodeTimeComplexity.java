package com.yufeng.leetcode.complexity;


/**
 * @description
 *      常见的复杂度分析
 * @author yufeng
 * @create 2019-09-01
 */
public class CommonCodeTimeComplexity {

    /**
     * 数组交换
     * 时间复杂度: O(1)
     */
    public void swap(Object[] arr, int i, int j) {
        if (i < 0 || i >= arr.length) {
            throw new IllegalArgumentException("i is out of bound.");
        }
        if (j < 0 || j >= arr.length) {
            throw new IllegalArgumentException("j is out of bound.");
        }

        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 求和
     * 时间复杂度: O(n)
     */
    public int sum(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n should be greater or equal to zero");
        }
        int ret = 0;
        for (int i = 0; i <= n; i ++) {
            ret += i;
        }
        return ret;
    }

    /**
     * 数组反转
     * 时间复杂度: O(n)
     */
    public void reverse(Object[] arr) {
        int n = arr.length;
        for (int i = 0; i < n / 2; i ++) {
            swap(arr, i, n - 1 - i);
        }
    }

    /**
     * 选择排序法
     * 时间复杂度: O(n^2)
     */
    public void selectionSort(Comparable[] arr, int n) {
        for (int i = 0; i < n; i ++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j ++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 内层函数是常数阶
     * 时间复杂度: O(n)
     */
    public void printInformation(int n) {
        for (int i = 1; i <= n; i ++) {
            for (int j = 1; j <= 30; j ++) {
                System.out.println("Class" + i + "-" + "No." + j);
            }
        }
    }

    /**
     * 二分查找法
     * 时间复杂度: O(logn)
     */
    public int binarySearch(Comparable[] arr, int n, int target) {
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) == 0) {
                return mid;
            } else if(arr[mid].compareTo(target) > 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 将数字整型转换为字符串
     * 时间复杂度: O(logn)
     */
    public String intToString(int num) {
        StringBuilder s = new StringBuilder();
        String sign = "+";
        if (num < 0) {
            num = -num;
            sign = "-";
        }

        while (num != 0) {
            s.append(Character.getNumericValue('0') + num % 10);
            num /= 10;
        }

        if (s.length() == 0) {
            s.append('0');
        }
        s.reverse();
        if (sign == "-") {
            return sign + s.toString();
        } else {
            return s.toString();
        }
    }

    /**
     * 注意外层循环增量的变化
     * 时间复杂度: O(n*logN)
     */
    public void hello(int n) {
        for (int sz = 1; sz < n; sz += sz) {
            for (int i = 1; i < n; i ++) {
                System.out.println("Hello, Algorithm!");
            }
        }
    }

    /**
     * 判断是否是素数
     * 时间复杂度: O(sqrt(n))
     */
    public boolean isPrime(int num) {
        for (int x = 2; x * x <= num; x ++) {
            if (num % x == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否是素数(优化)
     * 时间复杂度: O(sqrt(n))
     */
    public boolean isPrime2(int num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }

        for (int x = 3; x * x <= num; x += 2) {
            if (num % x == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CommonCodeTimeComplexity c = new CommonCodeTimeComplexity();

        System.out.println(c.intToString(123));
        System.out.println(c.intToString(0));
        System.out.println(c.intToString(-123));

        System.out.println();
        if (c.isPrime2(137)) {
            System.out.println("137 is a prime");
        } else {
            System.out.println("137 is not a prime");
        }

        if (c.isPrime(121)) {
            System.out.println("121 is a prime");
        } else {
            System.out.println("121 is not a prime");
        }

        c.hello(4);
    }
}


