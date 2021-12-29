package com.yufeng.extend.generic.impl;

import com.yufeng.extend.generic.GenericInterface;

/**
 * @description
 *      1. 泛型接口使用方式(二): 实现类直接指定具体类型
 *      2. jdk中代码举例:
 *         2-1 public interface iterator<E> {
 *                  E next();
 *             }
 *         2-2 public final class Scanner implements Iterator<String> {
 *                  public String next() {}
 *             }
 * @author yufeng
 * @create 2019-07-03
 */
public class GenericInterfaceImpl2 implements GenericInterface<String> {

    @Override
    public void show(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        GenericInterfaceImpl2 g = new GenericInterfaceImpl2();
        g.show("@GenericInterfaceImpl2");
//        g.show(30);
    }
}
