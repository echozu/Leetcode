package 数据结构.QueueOrStack;

import java.util.LinkedList;

//双端队列的实现:本来队列就像是排队，现在的话双端队列就像是过天桥，可以从两边进和出。但失去了队列的特点：先进先出。
public class Deque {
    //用链表实现双端队列
    class MyListDeque<E> {
        private LinkedList<E> list = new LinkedList<>();

        // 从队头插入元素，时间复杂度 O(1)
        void addFirst(E e) {
            list.addFirst(e);
        }

        // 从队尾插入元素，时间复杂度 O(1)
        void addLast(E e) {
            list.addLast(e);
        }

        // 从队头删除元素，时间复杂度 O(1)
        E removeFirst() {
            return list.removeFirst();
        }

        // 从队尾删除元素，时间复杂度 O(1)
        E removeLast() {
            return list.removeLast();
        }

        // 查看队头元素，时间复杂度 O(1)
        E peekFirst() {
            return list.getFirst();
        }

        // 查看队尾元素，时间复杂度 O(1)
        E peekLast() {
            return list.getLast();
        }
    }
    class ArrayToDeque<E> {
        private CircularArray<E> arr = new CircularArray<>();

        // 从队头插入元素，时间复杂度 O(1)
        void addFirst(E e) {
            arr.addFirst(e);
        }

        // 从队尾插入元素，时间复杂度 O(1)
        void addLast(E e) {
            arr.addLast(e);
        }

        // 从队头删除元素，时间复杂度 O(1)
        E removeFirst() {
            return arr.removeFirst();
        }

        // 从队尾删除元素，时间复杂度 O(1)
        E removeLast() {
            return arr.removeLast();
        }

        // 查看队头元素，时间复杂度 O(1)
        E peekFirst() {
            return arr.getFirst();
        }

        // 查看队尾元素，时间复杂度 O(1)
        E peekLast() {
            return arr.getLast();
        }
    }

}
