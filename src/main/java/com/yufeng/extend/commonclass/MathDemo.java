package com.yufeng.extend.commonclass;

/**
 * @description
 *      1. Math类常用方法复习掌握
 * @author yufeng
 * @create 2019-09-05
 */
public class MathDemo {

    public void mathTest1() {
        /** public static int abs(int a): 返回参数的绝对值 */
        System.out.println(Math.abs(66));
        System.out.println(Math.abs(-66));
        System.out.println("----------------------");

        /** public static double ceil(double a): 返回大于等于参数的最小double整数值 */
        System.out.println(Math.ceil(14.777));
        System.out.println(Math.ceil(14.111));
        System.out.println("----------------------");

        /** public static double floor(double a): 返回小于或等于参数的最大double整数值 */
        System.out.println(Math.ceil(14.777));
        System.out.println(Math.ceil(14.111));
        System.out.println("----------------------");

        /** public static int round(double a): 按照四舍五入返回最接近参数的int值 */
        System.out.println(Math.round(13.4999));
        System.out.println(Math.round(13.5000));
        System.out.println("----------------------");

        /** public static int max(int a, int b): 返回两个int值中较大的那个值 */
        System.out.println(Math.max(19891224, 20181220));
        System.out.println("----------------------");

        /** public static int min(int a, int b): 返回两个int值中较小的那个值 */
        System.out.println(Math.min(19891224, 20181220));
        System.out.println("----------------------");

        /** public static double pow(double a, double b): 返回a的b次幂的值 */
        System.out.println(Math.pow(2, 4));
        System.out.println("----------------------");

        /** public static double random(): 返回值为double的正值 [0.0, 1.0) */
        System.out.println(Math.random());
        System.out.println((int)(Math.random() * 100));
        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        MathDemo mathDemo = new MathDemo();
        mathDemo.mathTest1();
    }

}
