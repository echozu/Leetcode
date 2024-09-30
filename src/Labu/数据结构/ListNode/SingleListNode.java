package Labu.数据结构.ListNode;

import java.util.NoSuchElementException;

// 单链表
// pre:表示前一个节点/前一次循环的东西
// cur:标识当前节点/当前循环
public class SingleListNode<E> {
    public static class Node<E> {
        // 单链表
        E val;
        Node<E> next;

        public Node(E x) {
            this.val = x;
            this.next = null;
        }
    }

    // 虚拟头节点
    private Node<E> head;
    // 实际尾节点，如果有这个的话，那么在记录时，应该要时刻注意尾部节点的更新
    private Node<E> tail;
    private int size;

    // 初始化一个链表
    // TODO: 修改 - 构造函数不需要传入head
    public SingleListNode() {
        // 给虚拟头节点设置为null
        this.head = new Node<>(null);
        // TODO: 修改 - 初始化 tail 为虚拟头节点
        this.tail = this.head;
        // 表示头节点不算作第一个数量
        this.size = 0;
    }

    // 增加头节点【虚拟头节点以下的第一个节点】
    public void addFirst(E e) {
        // 将传来的东西变成节点
        Node<E> first = new Node<>(e);
        first.next = this.head.next;
        head.next = first;
        // 判断更新尾部节点
        if (size == 0) {
            tail = first;
        }
        // 更新数量
        this.size++;
    }

    // 增加尾部节点
    public void addLast(E e) {
        Node<E> last = new Node<>(e);
        tail.next = last;
        tail = last;
        size++;
    }

    // 给某个索引index的后面添加节点
    public void add(int index, E element) {
        // TODO: 将索引检查逻辑融入代码
        if (index < 0 || index > size) { // 检查index是否为有效的插入位置
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == size) {
            addLast(element);
            return;
        }

        Node<E> newNode = new Node<>(element);
        Node<E> cur = head;
        // 找到当前节点
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        newNode.next = cur.next;
        cur.next = newNode;
        size++;
    }

    // 移除头部节点【虚拟头节点下的第一个节点】
    public void removeFirst() {
        if (size == 0) return;

        Node<E> cur = head.next;
        head.next = cur.next;

        // 如果size=1的话，那么删除后，链表即为空，所以此时得更新尾节点tail为head空节点
        // 注意：如果此时size=2的话，即使增加了节点，但是对尾节点并没有影响，所以不需要更新
        if (size == 1) {
            // TODO: 修改 - 更新 tail 为 head
            tail = head;
        }

        size--;
    }

    // 删除尾节点
    public void removeLast() {
        if (size == 0) {
            return;
        }

        Node<E> cur = head;
        // 得到尾节点的前一个节点
        for (int i = 1; i < size; i++) {
            cur = cur.next;
        }
        cur.next = null;
        tail = cur;
        size--;
    }

    // 删除某个节点
    public void remove(int index) {
        // TODO: 将索引检查逻辑融入代码
        if (index < 0 || index >= size) { // 检查index是否为有效的删除位置
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        // TODO: 修改 - 调整边界条件
        if (index == size - 1) {
            removeLast();
            return;
        }
        // TODO: 修改 - 调整边界条件
        if (index == 0) {
            removeFirst();
            return;
        }

        Node<E> prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = prev.next.next;
        size--;
    }

    // ***** 查 *****

    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.next.val;
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return getNode(size - 1).val;
    }

    public E get(int index) {
        // TODO: 将索引检查逻辑融入代码
        if (index < 0 || index >= size) { // 检查index是否为有效的访问位置
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> p = getNode(index);
        return p.val;
    }

    // ***** 改 *****

    public E set(int index, E element) {
        // TODO: 将索引检查逻辑融入代码
        if (index < 0 || index >= size) { // 检查index是否为有效的修改位置
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> p = getNode(index);

        E oldVal = p.val;
        p.val = element;

        return oldVal;
    }

    // ***** 其他工具函数 *****
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 返回 index 对应的 Node
    // 注意：请保证传入的 index 是合法的
    private Node<E> getNode(int index) {
        Node<E> p = head.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }
}
