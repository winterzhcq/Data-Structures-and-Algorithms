

/**
 * @author winter
 * @date 2019/7/14 15:02
 */
public class LinkedListMap<K,V> implements Map<K,V> {
    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value){
            this(key, value, null);
        }

        public Node(){
            this(null, null, null);
        }

        @Override
        public String toString(){
            return key.toString() + ":" + value.toString();
        }
    }
    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        // 在链表头添加
        if (node == null){
            dummyHead.next = new Node(key,value,dummyHead.next);
            size++;
        }else {
            // key 相同就更新value值
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node node = getNode(key);
        Node pre = dummyHead;
        while (pre.next != null){
            if (key.equals(pre.next.key))
                break;
            pre = pre.next;
        }
        if (pre.next != null){
            Node delNode = pre.next;
            pre.next = pre.next.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
     Node node = getNode(key);
     return node == null ? null:node.value;
    }
    private Node getNode(K key){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.key.equals(key))
                return cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node != null)
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
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while(cur != null){
            res.append("<" + cur + ">" + " -> ");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }
}
