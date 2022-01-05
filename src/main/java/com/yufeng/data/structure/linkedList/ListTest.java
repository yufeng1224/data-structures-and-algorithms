package com.yufeng.data.structure.linkedList;


/**
 * @description
 *      单向链表测试类
 * @author yufeng
 * @create 2019-07-03
 */
public class ListTest {

    public static void main(String[] args) {
        // linkedListTest();
        jdkLinkedListTest();
    }

    /**
     * linkedListTest
     */
    public static void linkedListTest() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i ++) {
            list.addLast(i);
        }

        System.out.println(list);

        list.addNode(10, 10);
        list.addNode(5, 15);
        list.add(6, 16);

        System.out.println(list);
        System.out.println("list.getFirst(): " + list.getFirst());
        System.out.println("list.getLast(): " + list.getLast());
        System.out.println("list.getNode(): " + list.getNode(6));

        list.setNode(6, 100);
        System.out.println(list);

        list.removeNode(6);
        System.out.println(list);

        list.removeLast();
        System.out.println(list);
    }

    /**
     * jdkLinkedList
     */
    public static void jdkLinkedListTest() {
        JDKLinkedList<Integer> jdkLinkedList = new JDKLinkedList<>();
        for (int i = 0; i < 20; i ++) {
            jdkLinkedList.addFirst(i);
        }
        System.out.println(jdkLinkedList);
        jdkLinkedList.addLast(30);
        System.out.println(jdkLinkedList);

        System.out.println("jdkLinkedList.size(): " + jdkLinkedList.size());
        System.out.println(jdkLinkedList.get(jdkLinkedList.size() - 1));

        jdkLinkedList.removeFirst();
        jdkLinkedList.removeLast();

        System.out.println(jdkLinkedList);
    }

}
