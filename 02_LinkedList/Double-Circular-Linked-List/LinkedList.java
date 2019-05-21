
/**
 * 实现双向循环链表，没有循环
 * @author winter
 * @date 2019/5/20 10:48
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
        Node t = tail;
//        Node newNode = new Node(e);
//        newNode.next = f;
        Node newNode = new Node(e,tail,h);
        head = newNode;
        if (h == null)
            tail = newNode;

        else
            h.prev = newNode;
        if ( t != null )
            t.next = newNode;
        size++;
    }

    //在链表尾添加结点
    public void addLast(E e){
        Node h = head;
        Node t = tail;
        Node newNode = new Node(e,t,head);
        tail = newNode;
        if ( t == null )
            head = newNode;
        else
            t.next = newNode;
        if ( h != null )
            h.prev = newNode;
        size++;
    }

    //在指定下标中添加结点
    public void add(int index,E e){
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Add failed. Illegal index.");
        if ( index == 0 )
            addFirst(e);
        else if ( index == size )
            addLast(e);
        else {
            Node cur = getNode(index);
            Node next  = cur;
            Node pre = cur.prev;
            Node newNode = new Node(e,pre,next);
            next.prev = newNode;
            if (pre == null)
                head = newNode;
            else
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
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Add failed. Illegal index.");
        Node cur = getNode(index);
        Node oldNode = cur;
        cur.e = e;
        return oldNode.e;
    }

    //查找链表中是否包含指定元素
    public boolean contains(E e){
        Node cur = head;
        while (cur != tail){
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        if (e.equals(tail.e))
            return true;
        return false;
    }

    // 从链表中删除第index位置元素，返回删除元素的值
    public E remove(int index){
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Add failed. Illegal index.");
        if (index == 0)
            return removeFirst();
        else if (index+1 == size)
            return removeLast();
        else {
            Node oldNode = getNode(index);
            Node next = oldNode.next;
            Node pre = oldNode.prev;
            if (pre == tail)
                head = next;
            if (next == pre)
                tail = pre;

            pre.next = next;
            next.prev = pre;
            // help GC
            oldNode.prev = null;
            oldNode.next = null;
            size--;
            return oldNode.e;
        }
    }

    // 删除链表的头结点
    public E removeFirst(){
        if (size > 1) {
            Node oldhead = head;
            Node t = tail;
            Node next = oldhead.next;
            t.next = next;
            next.prev = t;
            head = next;
            // Help GC
            oldhead.next = null;
            oldhead.prev = null;
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
            Node h = head;
            Node preNode = tail.prev;
            preNode.next = head;
            head.prev = preNode;
            tail = preNode;
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
        for (Node x = head; x != null; x = x.next) {
            if (x.e.equals(e)){
                Node oldNode = x;
                Node next = oldNode.next;
                Node pre = oldNode.prev;
                if (oldNode == head){
                    removeFirst();
                }else if(oldNode == tail){
                    removeLast();
                }else {
                    pre.next = next;
                    next.prev = pre;
                    //Help GC
                    oldNode.next = null;
                    oldNode.prev = null;
                    oldNode.e = null;
                }
                size--;
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        //正向打印
//        if (head == tail)
//            res.append(tail+"->");
//        Node cur = head;
//        while(cur != tail){
//            res.append(cur + "->");
//            cur = cur.next;
//        }
//        if (tail != null && head != tail)
//            res.append(tail+"->");
////        res.append("NULL");
//        //res.append("head value is "+head.e);
//        res.append(" | list size is "+ size);
        //反向打印
        if (head == tail)
        res.append(tail+"->");
        Node cur = tail;
        while(cur != head){
            res.append(cur + "->");
            cur = cur.prev;
        }
        if (head != null && head != tail)
            res.append(head+"->");
        res.append("NULL");
        res.append(" | list size is "+ size);
          return res.toString();
    }
}
