package com.yufeng.data.structure.segment;

/**
 * 描述:
 *     融合器
 * @author yufeng
 * @create 2019-08-20
 */
public interface Merger<E> {

    E merge(E a , E b);

}
