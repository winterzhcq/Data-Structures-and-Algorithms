

/**
 * @author winter
 * @date 2019/6/19 20:09
 */
public interface Set<E> {
    void add(E e);
    boolean contains(E e);
    void remove(E e);
    int getSize();
    boolean isEmpty();
}
