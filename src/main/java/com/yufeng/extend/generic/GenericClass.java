package com.yufeng.extend.generic;

/**
 * @description
 *      1. 泛型是一个未知的数据类型, 当我们不确定是什么数据类型的时候, 可以使用泛型
 *      2. 泛型可以接收任意的数据类型, 我们在创建对象时确定泛型的数据类型
 * @author yufeng
 * @create 2019-07-03
 */
public class GenericClass<T> {

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        GenericClass<String> g1 = new GenericClass<>();
        g1.setT("GenericClass");
        System.out.println(g1.getT());

        GenericClass<Integer> g2 = new GenericClass<>();
        g2.setT(29);
        System.out.println(g2.getT());

        GenericClass<Boolean> g3 = new GenericClass<>();
        g3.setT(true);
        System.out.println(g3.getT());
    }
}
