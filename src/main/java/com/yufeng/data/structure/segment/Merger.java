package com.yufeng.data.structure.segment;

/**
 * @description
 *     融合器
 * @author yufeng
 * @create 2019-07-25
 */
public interface Merger<E> {

    E merge(E a , E b);

}
