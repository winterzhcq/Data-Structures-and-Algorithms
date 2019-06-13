

import java.util.Random;

/**
 * 当前跳表是针对于有序的整数链表而设计的
 * @author winter
 * @date 2019/6/13 11:11
 */
public class SkipList<E extends Comparable<E>> {
    /**
     * 跳表的结构，每一个数组存储的是
     * @param <E>
     */
    private static final class Node<E>{
        // 节点元素
        private E data;

        // next[i]的头结点表示每一级的的头结点，e.next[i]表示本级的下一个节点
        private Node next[];


        public Node(E data, int level) {
            this.data = data;
            this.next = new Node[level];
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // 跳表的最大级数
    private static final int MAX_LEVEL = 16;

    // 构造空的虚拟头节点，作为一个标记节点
    private final Node head = new Node(null,MAX_LEVEL);

    // 利用随机函数决定插入新节点在那个级
    private final Random random = new Random();

    // 当前跳表的索引级别
    private int level;

    // 跳表的大小
    private int size;

    public int size(){
        return size;
    }

    public Node get(E e){
        Node node = head;
        // 一个索引级一一个索引级的去找，直到找到第0级也就是原始链表不满足条件就结束循环
        for (int i = level - 1; i >= 0; i--) {
            // 如果当前索引级的当前节点比目标节点大者向当索引级的下一个节点去比较，当发现当前节点的值比e大就结束
            while (i < node.next.length
                    && node.next[i] != null
                    && e.compareTo((E)node.next[i].data) > 0){
                node = node.next[i];
            }
        }

        //判断该结点是不是目标节点
        if (node.next[0] != null && node.next[0].data.equals(e)) {
            return node.next[0];
        } else {
            return null;
        }
    }

    public void add(E e){
        Node node = get(e);
        if (node != null)
            return;

        // 将新插入的元素放在一个索引级上
        int newLevel = randomLevel();
        Node[] update = new Node[MAX_LEVEL];

        // 创建新的节点
        Node newNode = new Node(e,newLevel);
        node = head;
        for (int i = newLevel - 1; i >= 0; i--) {
            while (node.next[i] != null && e.compareTo((E)node.next[i].data)>0){
                node = node.next[i];
            }
            update[i] = node;
        }

        // 插入操作
        for (int i = 0; i < newLevel ; i++) {
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
        // 跟新跳表的level
        if (newLevel > level)
            level = newLevel;
        size++;
    }

    // 删除操作
    public void remove(E e) {
        Node[] update = new Node[level];
        Node node = head;
        for (int i = level - 1; i >= 0; --i) {
            while (node.next[i] != null && e.compareTo((E)node.next[i].data) > 0 ) {
                node = node.next[i];
            }
            update[i] = node;
        }

        if (node.next[0] != null && e.equals(node.next[0].data)) {
            for (int i = level - 1; i >= 0; --i) {
                if (update[i].next[i] != null && e.equals(update[i].next[i].data)) {
                    update[i].next[i] = update[i].next[i].next[i];
                }
            }
        }
        size--;
    }

    //一个简单的随机级数算法
    private int randomLevel() {
        // 以下实现与Redis ZSET实现基本相同
        // see https://github.com/antirez/redis/blob/4.0/src/t_zset.c

        int newLevel = 1;
        // 概率上的随机
        while ((random.nextInt() & 0xFFFF) < (0xFFFF >> 2)) {
            newLevel++;
        }
        return (newLevel < MAX_LEVEL) ? newLevel : MAX_LEVEL;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("skip list size"+size);
        str.append("\n");
        str.append("skip list level "+level);
        str.append("\n");
        Node node = head;
        for (int i = level - 1; i >= 0; i--) {
            str.append("head ->");
            while (node.next[i] != null) {
                str.append(node.next[i] + "->");
                node = node.next[i];
            }
            str.append("NULL");
            node = head;
            str.append("\n");
        }
        return str.toString();
    }
}
