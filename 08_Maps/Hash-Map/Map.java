

/**
 * @author winter
 * @date 2019/7/14 15:00
 */
public interface Map<K,V> {
    V add(K key, V value);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    void set(K key, V newValue);
    int getSize();
    boolean isEmpty();
}
