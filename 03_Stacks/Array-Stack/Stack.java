
/**
 * @author winter
 * @date 2019/5/26 17:10
 */
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    int search(E e);
    void push(E e);
    E pop();
    E peek();
}
