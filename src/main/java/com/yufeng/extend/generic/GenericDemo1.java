package com.yufeng.extend.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @description
 *      泛型通配符演示
 * @author yufeng
 * @create 2019-07-03
 */
public class GenericDemo1 {

    public static void main(String[] args) {
        Collection<Integer> list1 = new ArrayList<>();
        Collection<String> list2 = new ArrayList<>();
        Collection<Number> list3 = new ArrayList<>();
        Collection<Object> list4= new ArrayList<>();

        getElement1(list1);
//        getElement1(list2);
        getElement1(list3);
//        getElement1(list4);

//        getElement2(list1);
//        getElement2(list2);
        getElement2(list3);
        getElement2(list4);
    }

    /**
     * 泛型通配符上限, 此时的类型必须是Number类型或者Number的子类
     */
    public static void getElement1(Collection<? extends Number> collection) {
        System.out.println(collection);
    }

    /**
     * 泛型通配符下限, 此时的类型必须是Number类型或者Number的父类
     */
    public static void getElement2(Collection<? super Number> collection) {
        System.out.println(collection);
    }
}
