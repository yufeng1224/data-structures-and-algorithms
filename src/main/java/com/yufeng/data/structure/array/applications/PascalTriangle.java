package com.yufeng.data.structure.array.applications;

/**
 * 描述: Pascal's Triangle(杨辉三角)
 * 相关知识和规律:
 *    1. 每一行的第一个数和最后一个数都是1
 *    2. 除了开头和结尾的两个1，其他的数等于上面两个数的和
 *    3. 二项式的公式为: (a+b)^3 = a^3 + 3a^2 * b + 3a * b^2 + b^3   (1, 3, 3, 1)
 *    4. 图形举例
 *                    1
 *                  1   1
 *                1   2   1
 *              1   3    3   1
 *            1   4    6   4   1
 *          1   5   10   10   5   1
 *  @author yufeng
 * @create 2020-01-21
 */
public class PascalTriangle {

    public static int[][] pascalTriangle(int n) {
        int[][] pt = new int[n][];
        for (int i = 0; i < n; i ++) {
            pt[i] = new int[i+1];
            pt[i][0] = 1;
            for (int j = 1; j < i; j ++) {
                pt[i][j] = pt[i-1][j-1] + pt[i-1][j];
            }
            pt[i][i] = 1;
        }
        return pt;
    }


    public static void main(String[] args) {
        int[][] array = pascalTriangle(5);

        for (int i = 0; i < array.length; i ++) {
            for (int j = 0; j < array[i].length; j ++) {
                System.out.print(" " + array[i][j]);
            }
            System.out.println();
        }
    }
}
