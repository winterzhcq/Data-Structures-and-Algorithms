
/**
 * @author winter
 * @date 2019/5/17 23:44
 */
public class LinkedList<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node() {
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }
    //虚拟头结点
    private Node dummyHead;
    //链表长度
    private int size;

    public LinkedList(){
        dummyHead = new Node();
        size = 0;
    }

    //获取链表的长度
    public int size(){
        return size;
    }

    //判断链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 在链表头添加元素
    public void addFirst(E e){
        add(0,e);
    }

    // 在指定元素中添加链表节点
    public void add(int index, E e){
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Add failed. Illegal index.");

        Node preNode = dummyHead;
        for (int i = 0; i < index  ; i++) {
            preNode = preNode.next;
        }
        Node node = new Node(e);
        node.next = preNode.next;
        preNode.next = node;
        size++;

    }

    // 在链表末尾添加新的元素e
    public void addLast(E e){
        add(size, e);
    }

    //获取链表中的第index位置的元素
    public E get(int index ){

        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Add failed. Illegal index.");
        Node cur = dummyHead.next;
        for (int i = 0; i < index ; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    // 修改链表中的第index元素的值
    public E set(int index, E e){

        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Add failed. Illegal index.");
        Node cur = dummyHead.next;
        for (int i = 0; i < index ; i++) {
            cur = cur.next;
        }
        E oldValue = cur.e;
        cur.e = e;
        return oldValue;
    }

    //查找链表中是否包含指定元素
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while (cur != null){
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    // 从链表中删除第index位置元素，返回删除元素的值
    public E remove(int index){

        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Add failed. Illegal index.");

        Node preNode = dummyHead;
        for (int i = 0; i < index ; i++) {
            preNode = preNode.next;
        }
        Node oldNode = preNode.next;
        preNode.next = oldNode.next;
        //GC
        oldNode.next = null;
        size--;
        return oldNode.e;
    }

    // 从链表中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 从链表中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }

    //从链表中删除元素e
    public void removeElement(E e){
        Node preNode = dummyHead;
        while (preNode.next != null){
            if (preNode.next.e.equals(e))
                break;
            preNode = preNode.next;
        }
        if (preNode.next != null){
            Node delNode = preNode.next;
            preNode.next = delNode.next;
            //GC
            delNode.next = null;
            size--;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }
}
