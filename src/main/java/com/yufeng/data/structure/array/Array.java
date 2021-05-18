package com.yufeng.data.structure.array;


/**
 * 描述:
 *      自定义实现动态数组
 * @author yufeng
 * @create 2019-07-01
 */
public class Array<E> {

    // 可以通过 data.length 获得 capacity, 所以无需维护 capacity 字段
    private E[] data;

    // 实际存储的数据数量(size就有具体的语意, 相当于 index)
    private int size;


    /**
     * 无参构造函数, 默认数组容量为10
     */
    public Array() {
        this(10);
    }


    /**
     * 有参构造函数
     */
    public Array(int initCapacity) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException("Illegal initCapacity " + initCapacity);
        }  else {
            data = (E[]) new Object[initCapacity];
            size = 0;                               // 初始化数组中还没有元素
        }
    }


    /**
     * 用户传入静态数组
     */
    public Array(E[] arr) {
        data = (E[])new Object[arr.length];
        for (int i = 0 ; i < arr.length ; i ++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }


    /**
     * 获取数组实际存储的元素个数
     */
    public int getSize() {
        return size;
    }


    /**
     * 获取数组的容量
     */
    public int getCapacity() {
        return data.length;
    }


    /**
     * 判断数组是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }


    // 向数组末尾添加一个新元素
//    public void addLast(int e) {
//        if (size == data.length) {
//            throw new IllegalArgumentException("AddLast failed. Array is full");
//        }
//
//        // data[size++] = e; 本着每一行只执行一个功能的原则，来进行编码;
//        data[size] = e;
//        size ++;
//    }


    /**
     * 数组末尾添加一个新元素
     */
    public void addLast(E e) {
        add(size ,e);
    }


    /**
     * 在所有元素前添加一个元素(复用 add(int index ,int e) 方法);
     */
    public void addFirst(E e) {
        add(0, e);
    }


    /**
     * 在第index个位置插元素e
     */
    public void add(int index, E e) {

        // 保证 index 合法性
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size");
        }

        if (size == data.length) {
            resize( data.length << 2);
        }

        for (int i = size - 1; i >= index; i --) {
            data[i+1] = data[i];
        }
        data[index] = e;
        size ++;
    }


    /**
     * 获取index位置的元素
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal. ");
        }
        return data[index];
    }

    /**
     * 获取数组最后一位的元素
     */
    public E getLast() {
        return get(size - 1);
        // return data[size - 1];   // 此种写法是不合理! 因为size可以为0。
    }

    /**
     * 获取数组第一位的元素
     */
    public E getFirst() {
        return get(0);
    }


    /**
     * 设置指定index位置的元素
     */
    public void set(int index , E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal. ");
        }
        data[index] = e;
    }


    /**
     * 查找数组中是否包含元素e
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i ++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }


    /**
     * 查找数组中元素e所在的索引位置，如果不存在元素e，则返回-1
     */
    public int find (E e) {
        for (int i = 0; i < size; i ++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 查找数组中是否存在元素e, 如果有多个, 全部返回(索引位置)
     */
    public int[] findAll(E e) {

        int number = 0;                                 // 统计元素e出现的次数
        for (int index = 0; index < size; index ++) {
            if (data[index] == e) {
                number ++;
            }
        }

        if (number == 0) {
            return null;
        }

        int[] indexArr = new int[number];                // 将index索引位置存入数组并返回
        number = 0;
        for (int index = 0; index < size; index ++) {
            if (data[index] == e) {
                indexArr[number] = index;
                number ++;
            }
        }
        return indexArr;
    }



    /**
     * 从数组删除指定index位置的元素，并返回删除的元素
     */
    public E remove(int index) {

        if (index < 0 || index >= size) {                       // index合法性检查
            throw new IllegalArgumentException("delete failed. Index is illegal. ");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i ++ ) {
            data[i-1] = data[i];
        }
        size --;
        data[size] = null;          //loitering objects != memory leak   help GC

        /** data[size-1] = null; size -- */

        /**
         * 防止复杂度震荡
         * 出现问题的原因: removeLast 时 resize 过于着急（Eager）
         * 解决方案: lazy ———— 当元素个数只有总容量的 1/4 时， 数组容量减半;
         */
        if (size == data.length / 4 && data.length / 2 != 0)  {
            resize(data.length / 2);
        }

        return ret;
    }


    /**
     * 从数组中删除第一个元素，返回删除的元素
     */
    public E removeFirst() {
        return remove(0);
    }


    /**
     * 从数组中删除最后一个元素，返回删除的元素
     */
    public E removeLast() {
        return remove(size - 1);
    }


    /**
     * 从数组中删除e
     */
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }


    /**
     * 从数组中删除所有的e
     */
    public void removeAllElement(E e) {
        while (find(e) != -1) {
            int index = find(e);
            remove(index);
        }
    }


    /**
     * 交换不同索引位置的元素
     */
    public void swap(int i, int j) {

        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }


    /**
     * 打印数组
     */
    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append("[");

        // 需要使用size, 因为capacity可能会存在很多空元素。
        for (int i = 0; i < size; i ++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }

        res.append("]");
        return res.toString();
    }


    /**
     * 动态扩容
     */
    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i ++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
