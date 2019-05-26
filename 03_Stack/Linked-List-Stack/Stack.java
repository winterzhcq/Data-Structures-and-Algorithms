
/**
 * @author winter
 * @date 2019/5/26 23:15
 */
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    boolean search(E e);
    void push(E e);
    E pop();
    E peek();
}
