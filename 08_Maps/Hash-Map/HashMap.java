

/**
 * @author winter
 * @date 2019/7/14 20:50
 */
public class HashMap<K,V> implements Map<K,V>{
    private class Node<K,V>{
        public int hash;
        public K key;
        public V value;
        public Node next;

        public Node(int hash,K key, V value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public K getKey(){
            return key;
        }
        public V getValue(){
            return value;
        }
    }

    // 默认的容量是16
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    // 数组最大的容量
    static final int MAXIMUM_CAPACITY = 1 << 30;

    // 默认的加载因子是0.75
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private float loadFactor;
    private int initialCapacity;
    private int size;

    // 数组
    private Node[] table;

    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY,DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            this.initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        this.loadFactor = loadFactor;
        this.initialCapacity = initialCapacity;
        table = new Node[this.initialCapacity];
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public V add(K key, V value) {
        // 判断是否需要扩容
        if (size >= initialCapacity*loadFactor) {
            size = 0;
            table = resize(initialCapacity = (2 * initialCapacity));
        }
        int hash = hash(key);
        // 没有遇见hash冲突的时候
        Node<K,V> p; int i;

        if ((p = table[i = (initialCapacity - 1) & hash]) == null) {
            table[i] = new Node(hash, key, value, null);
            size++;
        }else{
            Node<K,V> e; K k;
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else {
                // 来自jkd1.8的hash
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = new Node(hash, key, value, null);
                        size++;
                        break;
                    }
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        return null;
    }

     int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    /**
     * 扩容的原理
     * @param newCap
     * @return
     */
    private  Node<K,V>[] resize(int newCap) {
        Node<K,V>[] newTab = new Node[newCap];
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        for (int i = 0; i < table.length ; i++) {
            Node<K,V> e;
            if ((e = table[i]) != null){
                Node<K,V> next;
                Node<K,V> loHead = null, loTail = null;
                Node<K,V> hiHead = null, hiTail = null;
                do {
                    if ((e.hash & oldCap) == 0) {
                        if (loTail == null)
                            loHead = e;
                        else
                            loTail.next = e;
                        loTail = e;
                    } else {
                        if (hiTail == null)
                            hiHead = e;
                        else
                            hiTail.next = e;
                        hiTail = e;
                    }
                    size++;
                    next = e.next;
                }while ((e = next) != null);
                if (loTail != null) {
                    loTail.next = null;
                    newTab[i] = loHead;
                }
                if (hiTail != null) {
                    hiTail.next = null;
                    newTab[i + oldCap] = hiHead;
                }
            }
        }
        return newTab;
    }


    @Override
    public V remove(K key) {
        Node<K,V> e;
        return (e = removeNode(hash(key), key)) == null ?
                null : e.value;
    }

    private Node<K,V> removeNode(int hash, K key) {
        Node<K,V>[] tab; Node<K,V> p; int n, index;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (p = tab[index = (n - 1) & hash]) != null) {
            Node<K, V> node = null, e;
            K k;
            V v;
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                node = p;
            else if ((e = p.next) != null) {
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key ||
                                    (key != null && key.equals(k)))) {
                        node = e;
                        break;
                    }
                    p = e;
                } while ((e = e.next) != null);
            }
            if (node != null) {
                if (node == p)
                    tab[index] = node.next;
                else
                    p.next = node.next;
                --size;
                return node;
            }
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(hash(key),key) != null;
    }

    @Override
    public V get(K key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    private Node<K,V> getNode(int hash, K key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node
                    ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(hash(key),key);
        if (newValue == null)
            throw new IllegalArgumentException(key + " doesn't exist!");
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
