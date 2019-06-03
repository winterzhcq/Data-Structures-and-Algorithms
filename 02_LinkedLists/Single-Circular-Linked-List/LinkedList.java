
/**
 * @author winter
 * @date 2019/5/18 15:08
 */
public class LinkedList<E> {
    private class Node{
        public E e;
        public Node next;
        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e,null);
        }

        public Node() {
            this(null,null);
        }
        @Override
        public String toString(){
            return e.toString();
        }
    }
    // 头结点
    private Node head;
    // 尾结点
    private Node tail;
    //链表长度
    private int size;

    public LinkedList(){
        head = tail = null;
        size = 0;
    }

    //获取链表长度
    public int size(){
        return size;
    }
    //判断链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //在链表头添加结点
    public void addFirst(E e){
        if (size == 0){
            Node node = new Node(e);
            head = tail = node;
            size++;
        }else {
            Node node = new Node(e);
            tail.next = node;
            node.next = head;
            head = node;
            size++;
        }
    }

    // 在指定下标中添加链表节点
    public void add(int index, E e){
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Add failed. Illegal index.");
        if (index == 0){
            addFirst(e);
        }else if (index == size -1){
            addLast(e);
        }else {
            Node preNode = head;
            for (int i = 0; i < index - 1; i++) {
                preNode = preNode.next;
            }
            Node node = new Node(e);
            node.next = preNode.next;
            preNode.next = node;
            size++;
        }
    }

    // 在链表尾结点添加元素
    public void addLast(E e){
        if (size == 0){
            Node node = new Node(e);
            head = tail = node;
            size++;
        }else {
            Node node = new Node(e);
            tail.next = node;
            node.next = head;
            tail = node;
            size++;
        }
    }

    //获取链表中的第index位置的元素
    public E get(int index ){

        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Add failed. Illegal index.");
        Node cur = head;
        for (int i = 0; i < index ; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    // 修改链表中的第index元素的值
    public E set(int index, E e){

        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Add failed. Illegal index.");
        Node cur = head;
        for (int i = 0; i < index ; i++) {
            cur = cur.next;
        }
        E oldValue = cur.e;
        cur.e = e;
        return oldValue;
    }

    //查找链表中是否包含指定元素
    public boolean contains(E e){
        Node cur = head;
        if (tail.e.equals(e))
            return true;
        while (cur != tail){
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
        if (index == 0){
            return removeFirst();
        }else if (index+1 == size){
            return removeLast();
        }else {
            Node preNode = head;
            for (int i = 0; i < index - 1; i++) {
                preNode = preNode.next;
            }
            Node oldNode = preNode.next;
            preNode.next = oldNode.next;
            //GC
            oldNode.next = null;
            size--;
            return oldNode.e;
        }
    }

    // 删除链表的头结点
    public E removeFirst(){
        if (size > 1) {
            Node oldhead = head;
            tail.next = oldhead.next;
            head = oldhead.next;
            // Help GC
            oldhead.next = null;
            size--;
            return oldhead.e;
        }else if (size == 1){
            Node oldNode = head;
            // Help GC
            head = tail = null;
            size --;
            return oldNode.e;
        }else {
            throw new IllegalArgumentException("There is no elements in the linked list.");
        }
    }

    //删除链表尾结点
    public E removeLast(){
        if (size > 1) {
            Node dummyHead = new Node();
            dummyHead = head;
            while (dummyHead.next != tail){
                dummyHead = dummyHead.next;
            }
            Node oldNode = dummyHead.next;
            dummyHead.next = head;
            tail = dummyHead;
            // Help GC
            oldNode.next = null;
            size--;
            return oldNode.e;
        }else if (size == 1){
            Node oldNode = head;
            // Help GC
            head = tail = null;
            size --;
            return oldNode.e;
        }else {
            throw new IllegalArgumentException("There is no elements in the linked list.");
        }
    }

    //从链表中删除元素e
    public void removeElement(E e){
        if (head.e.equals(e)){
            removeFirst();
        }else if (tail.e.equals(e)){
            removeLast();
        }else {
            Node preNode = head;
            while (preNode.next != tail) {
                if (preNode.next.e.equals(e))
                    break;
                preNode = preNode.next;
            }
            if (preNode.next != null) {
                Node delNode = preNode.next;
                preNode.next = delNode.next;
                //GC
                delNode.next = null;
                size--;
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        if (head  == null){
            res.append("NULL");
            return res.toString();
        }
        Node cur = head;
        while(cur != tail){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append(tail.e + "->");
        res.append("head value is "+head.e);
        res.append(" | list size is "+ size);
        return res.toString();
    }
}
