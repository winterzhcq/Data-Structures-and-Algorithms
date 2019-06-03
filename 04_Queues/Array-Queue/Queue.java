
/**
 * @author winter
 * @date 2019/5/30 11:35
 */
public interface Queue<E> {
    int size();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E peek();
}
