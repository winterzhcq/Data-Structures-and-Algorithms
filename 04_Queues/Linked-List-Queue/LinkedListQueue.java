
/**
 * 使用双向链表的结构实现队列
 * @author winter
 * @date 2019/5/31 23:48
 */
public class LinkedListQueue<E> implements Queue<E>{
    private LinkedList<E> list;

    public LinkedListQueue() {
        list = new LinkedList<>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        list.addFirst(e);
    }

    @Override
    public E dequeue() {
        return list.removeLast();
    }

    @Override
    public E peek() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty !");
        return list.get(0);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
