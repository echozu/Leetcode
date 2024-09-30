package Labu.数据结构.HashMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 使用拉链法实现哈希表
public class ChainingHashMap<K,V> {
    // 拉链法使用的单链表节点，存储 key-value 对
    private static class KVNode<K, V> {
        K key;
        V value;
        // 因为我们使用了内置的 LinkedList 类，所以不用 next 指针
        // 不用我们自己实现链表的逻辑

        KVNode(K key, V val) {
            this.key = key;
            this.value = val;
        }
    }

    // 哈希表的底层数组，每个数组元素是一个链表，链表中每个节点是 KVNode 存储键值对
    private LinkedList<KVNode<K, V>>[] table;

    // 哈希表中存入的键值对个数
    private int size;
    // 底层数组的初始容量
    private static final int INIT_CAP = 4;

    public ChainingHashMap() {
        this(INIT_CAP);
    }
    public ChainingHashMap(int initCapacity) {
        size = 0;
        // 保证底层数组的容量至少为 1，因为 hash 函数中有求余运算，避免出现除以 0 的情况
        initCapacity = Math.max(initCapacity, 1);
        // 初始化哈希表
        table = (LinkedList<KVNode<K, V>>[]) new LinkedList[initCapacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
    }
    // **** 增/改 ****

    // 添加 key -> val 键值对
    // 如果键 key 已存在，则将值修改为 val
    public void put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        //根据传来的key获得index，并根据这个得到哈希表中原有的链表
        int index = hash(key);
        LinkedList<KVNode<K,V>> list=table[index];
        //遍历该list链表，得到每一个节点node
        for (KVNode<K, V> node : list) {
            //进行判断key是否已经存在
            if(node.key.equals(key)){
                //更新值
                node.value=val;
                return;
            }
        }
        //如果不存在，则新增节点
        list.add(new KVNode<>(key,val));
        size++;
        //todo:如果元素超出了扩容因子，则进行扩容
        if(size>table.length*0.75){
            resize(table.length*2);
        }
    }
    // **** 删 ****

    // 删除 key 和对应的 val
    public void remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        LinkedList<KVNode<K, V>> list = table[hash(key)];
        // 如果 key 存在，则删除，size 减少
        for (KVNode<K, V> node : list) {
            if (node.key.equals(key)) {
                list.remove(node);
                size--;

                // todo:缩容，当负载因子小于 0.125 时，缩容
                if (size <= table.length / 8) {
                    resize(table.length / 4);
                }
                return;
            }
        }
    }
    // **** 查 ****

    // 返回 key 对应的 val，如果 key 不存在，则返回 null
    public V get(K key) {
        if(key==null){
            throw new IllegalArgumentException("key is null");
        }
        int index = hash(key);
        LinkedList<KVNode<K, V>> list = table[index];
        for (KVNode<K, V> node : list) {
            if(node.equals(key)){
                return node.value;
            }
        }
        return null;
    }
    // 返回所有 key
    public List<K> keys() {
        List<K> ks=new ArrayList<>();
        for (LinkedList<KVNode<K, V>> linkedList : table) {
            for (KVNode<K, V> node : linkedList) {
                ks.add(node.key);
            }
        }
        return ks;
    }
    // **** 其他工具函数 ****

    public int size() {
        return size;
    }

    // 哈希函数，将键映射到 table 的索引
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % table.length;
    }
    //扩容操作,对table的长度进行扩容，那里面的链表怎么办？进行照搬
    private void resize(int newCap) {
        // 构造一个新的 HashMap
        // 避免 newCap 为 0，造成求模运算产生除以 0 的异常
        newCap = Math.max(newCap, 1);
        ChainingHashMap<K, V> newMap = new ChainingHashMap<>(newCap);
        // 穷举当前 HashMap 中的所有键值对
        for (LinkedList<KVNode<K, V>> list : table) {
            for (KVNode<K, V> node : list) {
                // 将键值对转移到新的 HashMap 中
                newMap.put(node.key, node.value);
            }
        }
        // 将当前 HashMap 的底层 table 换掉
        this.table = newMap.table;
    }
}
