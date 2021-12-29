package com.yufeng.extend.generic.impl;

import com.yufeng.extend.generic.GenericInterface;

/**
 * @description
 *      1. 泛型接口使用方式(一): 接口使用什么泛型, 实现类就使用什么泛型, 类跟着接口走
 *         相当于定义了含有泛型的类
 *      2. jdk中代码举例:
 *         2-1  public interface List<E> {
 *                 boolean add(E e);
 *                 E get(int index);
 *              }
 *         2-2  public class ArrayList<E> implements List<E> {
 *                 public boolean add(E e) {}
 *                 public E get(int index) {}
 *              }
 * @author yufeng
 * @create 2019-07-03
 */
public class GenericInterfaceImpl1<T> implements GenericInterface<T> {

    @Override
    public void show(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        GenericInterfaceImpl1 g = new GenericInterfaceImpl1();
        g.show("GenericInterfaceImpl1");
        g.show(29);
        g.show(true);
        g.show(12.34);
    }
}
