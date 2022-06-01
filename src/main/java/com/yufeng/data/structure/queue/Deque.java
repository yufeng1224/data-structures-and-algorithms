package com.yufeng.data.structure.queue;

/**
 * @description
 *      实现双端队列
 * @author yufeng
 * @create 2019-07-07
 */
public class Deque<E> {

    private E[] data;

    private int front, tail;                        // tail下标位置上是没有元素的。front==tail时, 队列是满的

    private int size;                               // 记录存储的元素数量

    public Deque(int capacity) {
        data = (E[])new Object[capacity];           // 双端队列将不浪费空间
        front = 0;
        tail = 0;
        size = 0;
    }

    public Deque() {
        this(10);
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * 队尾添加元素: 与之前实现的队列中的enqueue()逻辑一致
     */
    public void addLast(E e) {
        if (size == getCapacity()) {
            resize (getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    /**
     * 队首添加元素
     */
    public void addFront(E e) {
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }

        /**
         * 确定添加新元素的索引位置, 也就是front - 1
         * 如果 front == 0, 新的位置是 data.length - 1
         */
        front = front == 0 ? data.length - 1 : front - 1;
        data[front] = e;
        size ++;
    }

    /**
     * 队首删除元素
     */
    public E removeFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Can't dequeue from an empty queue.");
        }

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;

        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    /**
     * 队尾删除元素
     */
    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Can't dequeue from an empty queue");
        }

        // tail 下标对应是没有元素的。 因此需要往前减去1。 计算删除掉队尾元素以后, 新的 tail 位置
        tail = tail == 0 ? data.length - 1 : tail - 1;
        E ret = data[tail];
        data[tail] = null;
        size --;

        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }

        return data[front];
    }

    public E getLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }

        // 因为tail指向的是队尾元素的下一个位置, 所以需要计算一下真正队尾元素的索引
        int index = tail == 0 ? data.length - 1 : tail - 1;
        return data[index];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i <= size - 1; i ++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder res  = new StringBuilder();
        res.append(String.format("Queue size = %d , capacity = %d\n", getSize(), getCapacity()));
        res.append("Deque front [");
        for (int i = 0; i < size; i ++) {
            res.append(data[(i + front) % data.length]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
