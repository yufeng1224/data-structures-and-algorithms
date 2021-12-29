package com.yufeng.extend.generic;

/**
 * @description
 *      泛型方法演示
 * @author yufeng
 * @create 2019-07-03
 */
public class GenericMethod {

    public <T> void show(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        GenericMethod g = new GenericMethod();
        g.show("GenericMethod");            // String类型
        g.show(29);                         // Integer类型
        g.show(true);                       // Boolean类型
        g.show(12.34);                      // Double类型
    }
}
