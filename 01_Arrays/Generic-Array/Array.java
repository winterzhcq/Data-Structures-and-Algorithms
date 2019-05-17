
/**
 * @author winter
 * @date 2019/5/17 15:53
 */
public class Array<E> {

    private E[] data;
    //定义数组的长度
    private int length;
    //定义数组的实际个数
    private int size;

    // 构造函数，构造初始数组的大小
    public Array(int capacity){
        data = (E[]) new Object[capacity];
        this.size = 0;
        this.length = capacity-1;
    }
    // 无参构造函数，设置默认数组的容量capacity=10
    public Array(){
        this(10);
    }

    //获取数组的长度
    public int length(){
        return length;
    }

    //获取数组的元素个数
    public int size(){
        return size;
    }

    // 返回数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //在数组指定索引index下标插入一个元素e
    public void add(int index, E e){

        //数组空间满了
        if (size == data.length)
            throw new IllegalArgumentException("Add failed. Array is full.");

        //插入位置不合法
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Add failed. Require index >= 0 and index <= size.");

        //可以插入
        for (int i = size ; i >= index ; i --) {
            data[i] = data[i-1];
        }
        data[index] = e;
        size++;
    }

    //插入一个元素
    public boolean add(E e) {
        if (size + 1 == data.length)
            throw new IllegalArgumentException("Add failed. Array is full.");
        data[size++] = e;
        return true;
    }

    // 获取index索引位置的元素
    public E get(int index){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Get failed. Index is illegal.");
        return data[index];
    }

    // 修改index下标的元素为e
    public E set(int index, E e) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Get failed. Index is illegal.");

        E oldValue = data[index];
        data[index] = e;
        //返回修改前的值
        return oldValue;
    }

    // 查找数组中是否有元素e
    public boolean contains(E e){
        for(int i = 0 ; i < size ; i ++){
            if(data[i].equals(e))
                return true;
        }
        return false;
    }

    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e){
        for(int i = 0 ; i < size ; i ++){
            if(data[i].equals(e))
                return i;
        }
        return -1;
    }

    // 从数组中删除下标为index的元素，返回删除的元素
    public E remove(int index){

        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Get failed. Index is illegal.");
        E oldValue = data[index];
        for (int i = index + 1; i < size  ; i++) {
            data[i -1] = data[i];
        }
        // GC
        data[--size] = null;
        return oldValue;
    }

    // 从数组中删除元素e
    public boolean removeElement(E e){
        int index = find(e);
        if(index != -1) {
            remove(index);
            return true;
        }
        return false;
    }
    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(data[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
