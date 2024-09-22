package 数据结构.QueueOrStack;

import javax.swing.tree.TreeNode;

//基本介绍：
//队列：先进先出【排队，先来的先买先走】
//栈：先进后出【摞盘子，先放的在最下面，下面是封住的，不能出，所以下面的后出，先进后出】
//todo：队列只允许在队尾插入元素，在队头删除元素，栈只允许在栈顶插入元素，从栈顶删除元素
//      也就是说如果定义了什么作为栈顶[如链表尾】的话，那么插入/删除就都得从那里改变
//      但是在队列中可以定义队列头和尾【如链表头和尾，然后插入和删除分别】
public class API {
    // 队列的基本 API
    abstract class MyQueue<E> {
        // 向队尾插入元素，时间复杂度 O(1)
        abstract void push(E e);
        // 从队头删除元素，时间复杂度 O(1)
        abstract E pop();

        // 查看队头元素，时间复杂度 O(1)
        abstract E peek();

        // 返回队列中的元素个数，时间复杂度 O(1)
        abstract int size();
    }

    // 栈的基本 API
    abstract class MyStack<E> {
        // 向栈顶插入元素，时间复杂度 O(1)
        abstract void push(E e);

        // 从栈顶删除元素，时间复杂度 O(1)
        abstract E pop();

        // 查看栈顶元素，时间复杂度 O(1)
        abstract E peek();

        // 返回栈中的元素个数，时间复杂度 O(1)
        abstract int size();
    }
}
