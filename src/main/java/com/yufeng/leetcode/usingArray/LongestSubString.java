package com.yufeng.leetcode.usingArray;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @description
 *      1. leetcode_3 解析
 *      2. 滑动窗口思想加强
 *      3. 知识点: ASCII码总共有256个
 * @author yufeng
 * @create 2019-09-04
 */
public class LongestSubString {

    /**
     * 1. 滑动窗口
     * 2. 时间复杂度: O(len(s)); 空间复杂度: O(len(charset))
     */
    public int lengthOfLongestSubstring1(String s) {
        int[] freq = new int[256];
        int res = 0;

        int l = 0, r = -1;                              // 滑动窗口s[l...r]
        /**
         * 整个循环从 l == 0, r == -1 这个空窗口开始
         * 到 l == s.length(); r = s.length() - 1 这个空窗口截止
         * 在每次循环里逐渐改变窗口, 维护freq, 并记录当前窗口中是否找到了一个新的最优值
         */
        while (l < s.length()) {
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                r ++;
                freq[s.charAt(r)] ++;
            } else {                                    // r已经到最右边或者freq[s[r + 1]] == 1, l向右滑动
                freq[s.charAt(l)] -- ;
                l ++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    /**
     * 1. 滑动窗口
     * 2. 时间复杂度: O(len(s)); 空间复杂度: O(len(charset))
     */
    public int lengthOfLongestSubstring2(String s) {
        int[] freq = new int[256];
        int res = 0;

        int l = 0, r = -1;
        while (r + 1 < s.length()) {
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                r ++;
                freq[s.charAt(r)] ++;
            } else {                                    // freq[s[r + 1]] == 1
                freq[s.charAt(l)] --;
                l ++;
            }
            res = Math.max(res, r - + 1);
        }

        return res;
    }

    /**
     * 滑动窗口另一种实现
     */
    public int lengthOfLongestSubstring3(String s) {
        int[] freq = new int[256];
        int res = 0;

        int l = 0, r = -1;
        while (r + 1 < s.length()) {
            while (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                r ++;
                freq[s.charAt(r)] ++;
            }
            res = Math.max(res, r - l + 1);

            if (r + 1 < s.length()) {
                r ++;
                freq[s.charAt(r)] ++;
                assert (freq[s.charAt(r)] == 2);
                while (l <= r && freq[s.charAt(r)] == 2) {
                    freq[s.charAt(l)] --;
                    l ++;
                }
            }
        }

        return res;
    }

    /**
     * 1. l每次可以向前跳跃, 而不仅仅是 +1, 但代价是, 为了获得这个跳跃的位置, 每次需要遍历整个窗口的字符串
     * 2. 时间复杂度: O(len(s)*len(charset)); 空间复杂度: O(1)
     */
    public int lengthOfLongestSubstring4(String s) {
        int res = 0;

        int l = 0, r = 0;
        while (r < s.length()) {
            int index = isDuplicateChar(s, l, r);

            /**
             * 如果s[r]之前出现过, l可以直接跳到s[r + 1]之前出现的位置 +1的地方
             */
            if (index != -1) {
                l = index + 1;
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    /**
     * 查看s[l...r-1]之间是否存在s[r],
     * 若存在, 返回相应的索引, 否则返回-1
     */
    private int isDuplicateChar(String s, int l, int r) {
        for (int i = l; i < r; i ++) {
            if (s.charAt(i) == s.charAt(r)) {
                return i;
            }
        }
        return  -1;
    }

    /**
     * 1. 滑动窗口
     * 2. 其中使用last[c]保存字符c上一次出现的位置, 用于在右边界发现重复字符时, 快速移动左边界
     * 3. 使用这种方法, 时间复杂度依然为O(n), 但是只需要动r指针, 实际上对整个s只遍历了一次
     * 4. 相较而言, 之前的方法, 需要移动l和r两个指针, 相对于对s遍历了两次
     */
    public int lengthOfLongestSubstring5(String s) {
        int res = 0;
        int[] last = new int[256];
        Arrays.fill(last, -1);

        int l = 0, r = -1;
        while (r + 1 < s.length()) {
            r ++;
            if (last[s.charAt(r)] != -1) {
                l = Math.max(l, last[s.charAt(r)] + 1);
            }
            res = Math.max(res, r - l + 1);
            last[s.charAt(r)] = r;
        }

        return res;
    }

    public static void testPerformace(String algoClassName, String algoName, String s){

        try{
            Class algoClass = Class.forName(algoClassName);
            Object solution = algoClass.newInstance();

            // 通过排序函数的Class对象获得排序方法
            Method algoMethod = algoClass.getMethod(algoName, String.class);

            long startTime = System.currentTimeMillis();
            // 调用算法
            Object resObj = algoMethod.invoke(solution, s);
            long endTime = System.currentTimeMillis();

            int res = (Integer)resObj;
            System.out.print(algoClassName + " : res = " + res + " ");
            System.out.println("Time = " + (endTime-startTime) + " ms" );
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LongestSubString l = new LongestSubString();
        System.out.println(l.lengthOfLongestSubstring1( "abcabcbb" ));
        System.out.println(l.lengthOfLongestSubstring2( "bbbbb" ));
        System.out.println(l.lengthOfLongestSubstring3( "pwwkew" ));
        System.out.println(l.lengthOfLongestSubstring4( "" ));
        System.out.println(l.lengthOfLongestSubstring5( "pwwkewabcad" ));

        int n = 10000000;

        StringBuilder s = new StringBuilder(n);
        for(int i = 0 ; i < n ; i ++) {
            s.append((char) (Math.random() * 95 + 32));
        }

        System.out.println("Test: 10,000,000 length of completely random string:");
        testPerformace("Solution1", "lengthOfLongestSubstring", s.toString());
        testPerformace("Solution2", "lengthOfLongestSubstring", s.toString());
        testPerformace("Solution3", "lengthOfLongestSubstring", s.toString());
        testPerformace("Solution4", "lengthOfLongestSubstring", s.toString());
        testPerformace("Solution5", "lengthOfLongestSubstring", s.toString());
    }
}
