
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * @author winter
 * @date 2019/11/5 21:57
 */
public class AC {
    private class Node{

        public boolean isWord;
        public TreeMap<Character, Node> next;
        public Node fail;
        public char c;
        public int length;
        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
            this.fail = null;
        }

        public Node() {
            this(false);
        }
    }
    private Node root;
    private int size;

    public AC(){
        root = new Node();
        size = 0;
    }

    // 获得Trie中存储的单词数量
    public int getSize(){
        return size;
    }

    // 向Trie中添加一个新的单词word
    public void add(String word){

        Node cur = root;
        for(int i = 0 ; i < word.length() ; i ++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null) {
                cur.next.put(c, new Node());
                cur.c = c;

            }
            cur = cur.next.get(c);
            cur.length = word.length();
        }

        if(!cur.isWord){
            cur.isWord = true;
            size ++;
        }
    }

    // 查询单词word是否在Trie中
    public boolean contains(String word){

        Node cur = root;
        for(int i = 0 ; i < word.length() ; i ++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    // 查询是否在Trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix){

        Node cur = root;
        for(int i = 0 ; i < prefix.length() ; i ++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return true;
    }


    public void buildFailurePointer() {
        Queue<Node> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            Node p = queue.remove();
            TreeMap<Character, Node> next = p.next;
            for ( Node node : next.values()) {
                Node pc = node;
                if (pc == null) continue;
                if (p == root) {
                    pc.fail = root;
                } else {
                    Node q = p.fail;
                    while (q != null) {
                        Node qc = q.next.get(pc.c);
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }


    public void match(String word) { // text是主串
        Node p = root;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            while (p.next.get(c) == null && p != root) {
                p = p.fail; // 失败指针发挥作用的地方
            }
            p = p.next.get(c);
            if (p == null) p = root; // 如果没有匹配的，从root开始重新匹配
            Node tmp = p;
            while (tmp != root) { // 打印出可以匹配的模式串
                if (tmp.isWord == true) {
                    int pos = i-tmp.length+1;
                    System.out.println("匹配起始下标" + pos + "; 长度" + tmp.length);
                }
                tmp = tmp.fail;
            }
        }
    }
}

