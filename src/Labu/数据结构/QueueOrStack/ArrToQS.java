package Labu.数据结构.QueueOrStack;
//这里<T>/<E>/<ABC>都可以表示泛型，只是人为规定的而已。
import Labu.数据结构.Array.CircularArray;

import java.util.ArrayList;

//用数组实现队列和栈
//队列和栈的四个基本方法：取得元素peek，push插入元素，size取得大小，pop删除元素
//而队列和栈的不同就是从队头弄还是从队尾弄了
public class ArrToQS<E> {

    //利用数组实现队列
    //队列：排队？先进先出：所以是从队尾插入元素，然后删除和取得都是从队头。
    //从队尾插入元素。
    //默认前面为队列尾，后面为队列头
    public class ArrToQueue<E> {
        private CircularArray<E> arr;

        public ArrToQueue() {
            arr = new CircularArray<>();
        }

        public void push(E t) {
            arr.addLast(t);
        }

        public E pop() {
            return arr.removeFirst();
        }

        public E peek() {
            return arr.getFirst();
        }

        public int size() {
            return arr.size();
        }
    }
    //数组实现栈：先入后出，将刚开始作为栈顶，则返回的也是栈顶的
    class ArrToStack{
        private ArrayList<E> list = new ArrayList<>();
        //添加栈顶元素
        public void push(E e){
            list.add(e);
        }
        //删除栈顶元素
        public E pop(){
            return list.remove(list.size()-1);
        }
        //弹出栈顶元素
        public E peek(){
            return list.get(list.size()-1);
        }
        public int size(){
            return list.size();
        }
    }
}
