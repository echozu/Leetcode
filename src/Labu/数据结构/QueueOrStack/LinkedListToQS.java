package Labu.数据结构.QueueOrStack;

import java.util.LinkedList;

//利用链表实现队列/栈
public class LinkedListToQS {
    // 用链表作为底层数据结构实现栈
    public class MyLinkedStack<E> {
        //以链表尾部为栈顶
        private LinkedList<E> list = new LinkedList<>();

        //todo：将链表尾作为栈顶
        // 向栈顶加入元素，时间复杂度 O(1)
        public void push(E e) {
            list.addLast(e);
        }

        // 从栈顶弹出元素，时间复杂度 O(1)
        public E pop() {
            return list.removeLast();
        }

        // 查看栈顶元素，时间复杂度 O(1)
        public E peek() {
            return list.getLast();
        }

        // 返回栈中的元素个数，时间复杂度 O(1)
        public int size() {
            return list.size();
        }
    }
    /*todo：相当于是把双链表的尾部作为栈顶，在双链表尾部增删元素的时间复杂度都是 O(1)，符合要求。*/
    // 用链表作为底层数据结构实现队列
    public class MyLinkedQueue<E> {
        private LinkedList<E> list = new LinkedList<>();
        //将链表头作为队头，链表尾作为队尾
        // 向队尾插入元素[这样子的话，越靠近队尾，那么插入的时间就越短，而靠近队头，插入的时间长，此时从链表头取元素，可实现先进先出】
        // 时间复杂度 O(1)
        public void push(E e){
            list.addLast(e);
        }
        // 从队头删除元素，时间复杂度 O(1)
        public E pop(){
            return list.removeFirst();
        }

        // 查看队头元素，时间复杂度 O(1)
        public E pead(){
            return list.getFirst();
        }

        // 返回队列中的元素个数，时间复杂度 O(1)
        public int size(){
            return list.size();
        }
    }
    /*todo：这段代码相当于是把双链表的尾部作为队尾，把双链表的头部作为队头，在双链表的头尾增删元素的复杂度都是 O(1)
    符合队列 API 的要求。*/


}
