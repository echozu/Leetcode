package Labu.数据结构.ListNode;

import java.util.NoSuchElementException;

//重构双链表的方法
public class DoubleListNode<E> {
    //虚拟头节点
    private Node head, tail;
    private int size;

    // 双链表节点
    private static class Node<E> {
        E val;
        //todo:每个节点有两个节点，所以在变化节点时需要注意变化两个节点
        //双节点的变化就是这里，其实就是给每个节点都定义多了一个前节点
        Node<E> next;
        //所以在每次变化后就不能仅仅只变化next指针了，prev也得变化
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    //构造虚拟头节点
    public DoubleListNode() {
        //todo：注意这里的头尾节点都是虚拟的，不具有实际意义，也就是说真正的链表头尾节点不是这个
        this.head = new Node(null);
        this.tail = new Node(null);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }

    //增加尾部接节点
    public void addLast(E e) {
        Node<E> newLast = new Node<>(e);
    /* 错，这里的tail是虚拟头节点不是真正的尾节点
    tail.next=newLast;*/
        //这个才是真正的尾节点
        Node<E> oldLast = tail.prev;
        oldLast.next = newLast;
        newLast.prev = oldLast;
        /*todo：如果tail是实际尾节点则需要这个，但此时tail是虚拟的，所以是下面那种
        tail = newLast;*/
        //改变虚拟尾节点的指针变化
        newLast.next = tail;
        tail.prev = newLast;
        size++;
    }

    //添加头部节点
    public void addFirst(E e) {
        Node<E> newFirst = new Node<>(e);
        Node<E> oldFirst = head.next;
        head.next = newFirst;
        newFirst.prev = head;
        newFirst.next = oldFirst;
        oldFirst.prev = newFirst;
        size++;
    }

    //增加节点，增加的是索引为index节点的下一个节点
    public void add(int index, E e) {
        //todo:注意要检查是否为0
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (size == index) {
            addLast(e);
            return;
        }

        Node<E> node = new Node<>(e);
        Node<E> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        //todo：这里排除了cur.next为null的情况，因为如果oldCurNext为null，则oldCurNext为尾部节点，即size=index，上面排除了
        Node<E> oldCurNext = cur.next;
        cur.next = node;
        node.prev = cur;
        node.next = oldCurNext;
        oldCurNext.prev = node;
       /* if (oldCurNext != null) {
            node.next = oldCurNext;
            //todo：因为这里用了oldCurNext.prev，所以得去判断oldCurNext是否为空
            oldCurNext.prev = node;
        } else {
            tail = node;
        }*/
        size++;
    }

    //移除尾节点
    public void removeLast() {
        //移除尾部节点
        //长度仅仅为1，其实就一个节点
        if (size == 1) {
            tail.prev = head;
            head.next = tail;
            size--;
            return;
        }
        //todo：找到尾节点，有双节点，所以不需要遍历从前往后找，只需要从虚拟尾节点截取即可
        Node last = tail.prev;
        Node pre = last.prev;
        pre.next = tail;
        tail.prev = pre;
        last.next = null;
        last.prev = null;
        size--;
        return;
    }

    //移除index节点
    public void remove(int index) {
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> cur = head.next;
        if (index == size) {
            removeLast();
            return;
        }
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        //也就是说这时要移除cur节点
        Node<E> oldCurPrev = cur.prev;
        Node<E> oldCurNext = cur.next;
        //todo：那有没有可能oldCurNext为空呢,不可能，上面去判断了
        oldCurPrev.next = oldCurNext;
        oldCurNext.prev = oldCurPrev;
        return;
    }
    // ***** 查 *****

    public E get(int index) {
        checkElementIndex(index);
        // 找到 index 对应的 Node
        Node<E> p = getNode(index);

        return p.val;
    }

    public E getFirst() {
        if (size < 1) {
            throw new NoSuchElementException();
        }

        return (E) head.next.val;
    }

    public E getLast() {
        if (size < 1) {
            throw new NoSuchElementException();
        }

        return (E) tail.prev.val;
    }

    // ***** 改 *****

    public E set(int index, E val) {
        checkElementIndex(index);
        // 找到 index 对应的 Node
        Node<E> p = getNode(index);

        E oldVal = p.val;
        p.val = val;

        return oldVal;
    }

    // ***** 其他工具函数 *****

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node<E> getNode(int index) {
        checkElementIndex(index);
        Node<E> p = head.next;
        // TODO: 可以优化，通过 index 判断从 head 还是 tail 开始遍历
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    // 检查 index 索引位置是否可以存在元素
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    // 检查 index 索引位置是否可以添加元素
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void display() {
        System.out.println("size = " + size);
        for (Node<E> p = head.next; p != tail; p = p.next) {
            System.out.print(p.val + " -> ");
        }
        System.out.println("null");
        System.out.println();
    }
}
