package com.yufeng.data.structure.linkedList;

/**
 * @description
 *      jdk中的linkedList仿写
 * @author yufeng
 * @create 2019-07-10
 */
public class JDKLinkedList<E> {

    private class Node {

        public E e;

        public Node prev;

        public Node next;

        public Node(E e) {
            this(null, e, null);
        }

        public Node(Node prev, E e , Node next) {
            this.e = e;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    /** 设置头结点和尾节点 */
    private Node first, last;

    private int size;

    public JDKLinkedList() {}

    /**
     * 获取链表中元素的数量
     */
    public int size() {
        return size;
    }

    /**
     * 获取链表头结点的值
     */
    public E getFirst() {
        Node f = first;
        if (f == null) {
            throw new IllegalArgumentException("Get failed！ The list is empty.");
        }
        return f.e;
    }

    /**
     * 获取链表尾结点的值
     */
    public E getLast() {
        Node l = last;
        if (l == null) {
            throw new IllegalArgumentException("Get failed！ The list is empty.");
        }
        return l.e;
    }

    /**
     * 在链表头添加新的元素e
     */
    public void addFirst(E e) {
        linkFirst(e);
    }

    /**
     * 在链表头添加新的结点
     */
    private void linkFirst(E e) {
        Node f = first;
        Node newNode = new Node(null, e, f);
        first = newNode;

        if (f == null) {        // 初始时first和last都为null, 所以需指向同一结点
            last = newNode;
        } else {
            f.prev = newNode;   // 原先的first结点, prev指针指向新的结点
        }

        size ++;
    }

    /**
     * 在链表尾添加新的元素e
     */
    public void addLast(E e) {
        linkLast(e);
    }

    /**
     * 在链表尾添加新的元素
     */
    private void linkLast(E e) {
        Node l = last;
        Node newNode = new Node(l, e, null);
        last = newNode;

        if (l == null) {            // 初始时first和last都为null, 所以需指向同一结点
            first = newNode;
        } else {
            l.next = newNode;       // 原先的last结点, next指针指向新的结点
        }

        size ++;
    }

    /**
     * 在指定index位置添加新的元素
     */
    public void add(int index, E e) {
        if (!isPositionIndex(index)) {
            throw new IllegalArgumentException("Add failed. Illegal Index");
        }

        if (index == size) {
            linkLast(e);
        } else {
            linkBefore(e, getNode(index));
        }
    }

    /**
     * 在指定index位置添加新的元素(succ为原先index位置的结点, 新元素插入后, succ将变为后继结点)
     */
    private void linkBefore(E e, Node succ) {
        Node prev = succ.prev;
        Node newNode = new Node(prev, e, succ);

        succ.prev = newNode;
        if (prev == null) {
            first = newNode;
        } else {
            prev.next = newNode;
        }

        size ++;
    }

    /**
     * index 位置合法性校验
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 获取index位置结点的值
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal Index.");
        }
        return getNode(index).e;
    }

    /**
     * 设置index位置结点的值
     */
    public E set(int index, E newVal) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal Index.");
        }

        Node node = getNode(index);
        E oldVal = node.e;
        node.e = newVal;

        return oldVal;
    }

    /**
     * 获取index位置的node
     */
    private Node getNode(int index) {
        if (index < (size >> 1)) {          // 判断index的位置来决定从头遍历还是从尾遍历
            Node x = first;
            for (int i = 0; i < index; i ++) {
                x = x.next;
            }
            return x;
        } else {
            Node x = last;
            for (int i = size - 1; i > index; i --) {
                x = x.prev;
            }
            return x;
        }
    }

    /**
     * 删除链表头结点, 并返回节点的值
     */
    public E removeFirst() {
        Node f = first;
        if (f == null) {
            throw new IllegalArgumentException("Remove failed. The list is empty");
        }
        return unlinkFirst(f);
    }

    /**
     * 删除链表头结点, 并返回结点的值
     */
    private E unlinkFirst(Node f) {
        E retVal = f.e;
        Node next = f.next;

        f.e = null;
        f.next = null;          // help GC

        first = next;
        if (next == null) {     // 链表已为空, last指向也要做更改
            last = null;
        } else {
            next.prev = null;
        }
        size --;
        return retVal;
    }

    /**
     * 删除链表尾结点
     */
    public E removeLast() {
        Node l = last;
        if (l == null) {
            throw new IllegalArgumentException("Remove failed. The list is empty");
        }
        return unlinkLast(l);
    }

    /**
     * 删除链表尾结点, 并返回结点的值
     */
    private E unlinkLast(Node l) {
        E retVal = l.e;
        Node prev = l.prev;

        l.e = null;
        l.prev = null;          // help GC

        last = prev;
        if (prev == null) {     // 链表已为空, first指向也要做更改
            first = null;
        } else {
            prev.next = null;
        }

        size --;
        return retVal;
    }

    /**
     * 删除index位置的结点, 并返回结点的值
     */
    public E remove(int index) {
        if (index < 0|| index >= size) {
            throw new IllegalArgumentException("Remove failed. Illegal Index");
        }
        return unlink(getNode(index));
    }

    /**
     * 删除链表中的结点x, 并返回节点的值
     */
    private E unlink(Node x) {
        E retVal = x.e;

        Node next = x.next;
        Node prev = x.prev;

        if (prev == null) {         // 待删除节点为头节点
            first = next;
        } else {
            prev.next = next;
            x.prev = null;          // x与prev断连
        }

        if (next == null) {         // 待删除节点为尾节点
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;          // x与next断连
        }

        x.e = null;
        size --;
        return retVal;
    }

    /**
     * 删除值等于e的节点
     */
    public boolean remove(E e) {
        if (e == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.e == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (e.equals(x.e)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Node cur = first; cur != null; cur = cur.next) {
            res.append(cur + "->");
        }
        res.append("NULL");
        return res.toString();
    }

}
