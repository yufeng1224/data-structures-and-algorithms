package com.yufeng.data.structure.linkedList.test;

import com.yufeng.data.structure.linkedList.DoubleLinkedList;


/**
 * 描述:
 *      自定义双向列表测试类
 * @author yufeng
 * @create 2019-07-05
 */
public class DoubleLinkedListTest {

    public static void main(String[] args) {
        doubleLinkedListTest01();
        doubleLinkedListTest02();
        test();
    }


    public static void doubleLinkedListTest01() {
        DoubleLinkedList<Integer> dLinkedList = new DoubleLinkedList<>();
        System.out.println("dLinkedList.getSize() : " + dLinkedList.getSize());
        System.out.println("dLinkedList.getSize() : " + dLinkedList.isEmpty());
        System.out.println(dLinkedList.toString());
        //System.out.println("dLinkedList.get(0) : " + dLinkedList.get(0));       // exception
    }


    public static void doubleLinkedListTest02() {
        DoubleLinkedList<Integer> dLinkedList = new DoubleLinkedList<>();

        for (int i = 0; i < 10; i ++) {
            dLinkedList.addLast(i);
        }

        System.out.println("dLinkedList.getSize() : " + dLinkedList.getSize());
        System.out.println("dLinkedList.isEmpty( : " + dLinkedList.isEmpty());
        System.out.println("dLinkedList.get(0) : " + dLinkedList.get(0));

        System.out.println("dLinkedList.addFirst(100)");
        dLinkedList.addFirst(100);
        System.out.println("dLinkedList.add(5, 15)");
        dLinkedList.add(5, 15);
        System.out.println(dLinkedList.toString());

        System.out.println("dLinkedList.getFirst()");
        System.out.println(dLinkedList.getFirst());
        System.out.println("dLinkedList.removeFirst");
        dLinkedList.removeFirst();
        System.out.println(dLinkedList.toString());

        dLinkedList.removeLast();
        System.out.println("dLinkedList.removeLast");
        System.out.println(dLinkedList.toString());
    }


    public static void test() {
        StringBuilder res = new StringBuilder();
        res.append("1");
        Node node = new Node();
        res.append(node);               // 报错java.lang.NullPointerException
        System.out.println(res.toString());
    }


    private static class Node<E> {

        public E e;

        public Node next;

        public Node() {
            this(null);
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

}
