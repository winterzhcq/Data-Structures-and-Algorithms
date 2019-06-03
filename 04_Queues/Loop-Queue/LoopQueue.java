

/**
 * @author winter
 * @date 2019/5/31 21:24
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    //获取数组容量
    public int getCapacity(){
        return data.length;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front)
            resize(getCapacity() * 2);
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {

        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size ; i++)
            newData[i] = data[(front+i) % data.length];
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        E ret = data[front];
        data[front] = null;
        front = (front +1 ) % data.length;
        size -- ;
        if (size == getCapacity() / 4 && getCapacity() / 2 !=0)
            resize(getCapacity()/2);
        return ret;
    }

    @Override
    public E peek() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }

    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for(int i = front ; i != tail ; i = (i + 1) % data.length){
            res.append(data[i]);
            if((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

}
