
/**
 * 实现双向链表，没有循环
 * @author winter
 * @date 2019/5/19 14:25
 */
public class LinkedList<E> {
    public class Node{
        public E e;
        public Node prev;
        public Node next;

        public Node(E e, Node prev, Node next) {
            this.e = e;
            this.prev = prev;
            this.next = next;
        }

        public Node(E e) {
            this(e,null,null);
        }

        public Node(){
            this(null,null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    //链表长度
    private int size;
    //链表头结点
    private Node head;
    //链表尾结点
    private Node tail;

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

        Node h = head;
//        Node newNode = new Node(e);
//        newNode.next = f;
        Node newNode = new Node(e,null,h);
        head = newNode;
        if (h == null)
            tail = newNode;
        else
            h.prev = newNode;
        size++;
    }

    //在链表尾添加结点
    public void addLast(E e){

        Node t = tail;
        Node newNode = new Node(e,t,null);
        tail = newNode;
        if ( t == null)
            head = newNode;
        else
            t.next = newNode;
        size++;
    }

    //在指定下标中添加结点
    public void add(int index,E e){
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Add failed. Illegal index.");
        if (index == 0){
            addFirst(e);
        }else if (index == size -1){
            addLast(e);
        }else {
            Node cur = getNode(index);
            Node next  = cur;
            Node pre = cur.prev;
            Node newNode = new Node(e,pre,next);
            next.prev = newNode;
            pre.next = newNode;
            size++;
        }
    }
    //获取链表中的第index位置的元素
    public E get(int index){

        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Add failed. Illegal index.");
        return getNode(index).e;
    }

    // 获取链表中的第index位置的节点，充分利用双向链表的优点
    private Node getNode(int index ){
        if (index < (size >> 2)){
            Node cur = head;
            for (int i = 0; i < index ; i++) {
                cur = cur.next;
            }
            return cur;
        }else {
            Node cur = tail;
            for (int i = size - 1; i > index ; i--) {
                cur = cur.prev;
            }
            return cur;
        }
    }

    // 修改链表中的第index元素的值
    public E set(int index, E e) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Add failed. Illegal index.");
        Node cur = getNode(index);
        Node oldNode = cur;
        cur.e = e;
        return oldNode.e;
    }

    //查找链表中是否包含指定元素
    public boolean contains(E e){
        Node cur = head;
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
        Node oldNode = getNode(index);
        Node next = oldNode.next;
        Node pre = oldNode.prev;
        if (pre == null){
            head = next;
        }else {
            pre.next = next;
            // help GC
            oldNode.prev = null;
        }
        if (next == null){
            tail = pre;
        }else {
            next.prev = pre;
            // help GC
            oldNode.next = null;
        }
        size--;
        return oldNode.e;
    }

    // 删除链表的头结点
    public E removeFirst(){
        if (size > 1) {
            Node oldhead = head;
            Node next = oldhead.next;
            head = next;
            // Help GC
            oldhead.next = null;
            next.prev = null;
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
            Node oldNode = tail;
            Node preNode = tail.prev;
            tail = preNode;
            // Help GC
            oldNode.next = null;
            preNode.next = null;
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
        for (Node x = head; x != null; x = x.next) {
            if (x.e.equals(e)){
                Node oldNode = x;
                Node next = oldNode.next;
                Node pre = oldNode.prev;
                if (pre == null){
                    head = next;
                }else {
                    pre.next = next;
                    // help GC
                    oldNode.prev = null;
                }
                if (next == null){
                    tail = pre;
                }else {
                    next.prev = pre;
                    // help GC
                    oldNode.next = null;
                }
                size--;
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        //正向打印
//        if (head  == null){
//            res.append("NULL");
//            res.append(" | list size is "+ size);
//            return res.toString();
//        }
//        Node cur = head;
//        while(cur != tail){
//            res.append(cur + "->");
//            cur = cur.next;
//        }
//        res.append(tail.e + "->");
//        res.append("head value is "+head.e);
//        res.append(" | list size is "+ size);
        //反向打印
            if (tail  == null){
            res.append("NULL");
            res.append(" | list size is "+ size);
            return res.toString();
        }
        Node cur = tail;
        while(cur != head){
            res.append(cur + "->");
            cur = cur.prev;
        }
        res.append(head.e + "->");
        res.append("tail value is "+tail.e);
        res.append(" | list size is "+ size);
        return res.toString();
    }
}
