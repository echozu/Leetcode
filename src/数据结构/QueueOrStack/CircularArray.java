package 数据结构.QueueOrStack;
// 实现环形数组
// 关键点：取余运算！
// todo：取余的快速运算：1.当 小的数 % 大的数时，结果就是小的数  如：2%4=2；
//                    2.当 大的数 % 小的数时，结果就是余数    如：5%2=1【5/2=2....1,所以结果就是1】
// 介绍：
/*  环形数组的关键在于，
它维护了两个指针 start 和 end，start 指向第一个有效元素的索引，end 指向最后一个有效元素的下一个位置索引。
   当我们在数组头部添加或删除元素时，只需要移动 start 索引，而在数组尾部添加或删除元素时，只需要移动 end 索引。
当 start, end 移动超出数组边界（< 0 或 >= arr.length）时，我们可以通过求模运算 % 让它们转一圈到数组头部或尾部继续工作
*/
//todo：定义的此环形数组为左闭右开：[start, end) 区间包含数组元素【所以在删除尾部元素时应该先移动索引，因为要将当前end所指引的位置视作开区间】

//todo：1.start=start+1)%size 是本来就要将索引右移的，取余后对于<size的start没有影响。
// 如何想到%：在写的时候想一下极端情况，如+1后是不是会超过数组索引，所以得去取余。
//todo:
//普通版往前后移动：往前移动：start=start-1
//             环形数组版：start=(start-1+size)%size;
//                     todo： +size是为了防止索引为负数，而为了抵消掉+size的影响，后面取余size
//普通版往后移动： 往后移动：end=end+1；
//              环形数组：end=(end+1)%size;
//                      todo： %size是为了防止+1后使得索引超出界限，所以取余。
//todo：其实，不管是(start-1+size)%size还是(end+1)%size在没有超过他们的范围时，所执行的仍然是start-1和end+1，并没有区别。
//只有当为负数/超出界限时，才会在这种规则的限制下绕回数组索引范围
public class CircularArray<T> {
    public T[] arr;
    public int start;
    public int end;
    //数组的大小
    public int size;
    //填进数组的数量
    public int count;

    public CircularArray() {
        this(1);
    }

    //初始化数组
    //注解的作用：让其忽略警告，因为默认数组是没有泛型，所以她会报警告，实际上我们已经给他处理了，所以加这个注解忽略。
    @SuppressWarnings("unchecked")
    public CircularArray(int size) {
        this.size = size;
        this.count = 0;
        // 因为 Java 不支持直接创建泛型数组，所以这里使用了类型转换
        this.arr = (T[]) new Object[size];
        this.start = 0;
        // 切记 end 是一个开区间，
        // 即 end 指向最后一个有效元素的下一个位置索引
        this.end = 0;
    }

    public static void main(String[] args) {
        CircularArray circularArray=new CircularArray();
    }

    //扩容
    @SuppressWarnings("unchecked")
    public void resize(int newSize) {
        // 创建新的数组
        T[] newArr = (T[]) new Object[newSize];
        //进行数组搬迁：
        for (int i = 0; i < count; i++) {
            //todo: 为什么这里不是arr[i]?
            newArr[i] = arr[(start + i) % size];
        }
        //更新数组
        arr = newArr;
        //更新大小
        this.size = newSize;
        //重置索引
        start = 0;
        end = count;
    }

    public void addFirst(T val) {
        //首先去判断数组是否被填满
        if (size > count) {
            //将数组扩容
            resize(size * 2);
        }
        // 第一次添加时，是添加进最后一个索引，然后之后去添加的话，是从后往前加
        //即： 第一次添加：【_,_,_,3]
        //     第二次添加：【_,_,2,3]依次
        start = (start - 1 + size) % size;
        arr[start] = val;
        count++;
    }

    //删除数组头部元素
    public T removeFirst() {
        //先判断数组是否为空
        if (count == 0) {
            System.out.println("数组为空");
            return null;
        }
        T value=arr[start];
        //再进行删除
        arr[start] = null;
        //todo:这里为什么要 %size是因为当start+1后可能会超过size，所以得%size。
        //索引移动
        start = (start + 1) % size;
        count--;
        //这里去判断数组长度是否小于四分之一，如果小于四分之一可以缩减数组长度。
        if (count < (size / 4)) {
            //这里去进行数组长度缩短
            resize(size / 2);
        }
        return value;
    }

    //添加进尾部
    public void addLast(T val) {
        if (size > count) {
            //将数组扩容
            resize(size * 2);
        }
        //这里是将元素填充到尾部，其实即从前往后添
        arr[end] = val;
        end = (end + 1) % size;
        count++;
    }

    //删除尾部
    public T removeLast() {
        //先判断数组是否为空
        if (count == 0) {
            System.out.println("数组为空");
            return null;
        }
        T t=arr[end];
        //因为end是开区间，所以应该是先移动索引后删除。
        end = (end - 1 + size) % size;
        arr[end] = null;
        count--;
        if (count < (size / 4)) {
            //这里去进行数组长度缩短
            resize(size / 2);
        }
        return t;
    }
    //获取尾部元素
    public T getLast(){
        return arr[(end-1+size)%size];
    }

    public boolean isFull() {
        return count == size;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public T getFirst() {
        return arr[start];
    }
}
