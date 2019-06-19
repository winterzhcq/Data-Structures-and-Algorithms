package cn.winter.datastructures.set.treeset;
import java.util.Stack;

/**
 * @author winter
 * @date 2019/6/15 18:46
 */
public class BST<E extends Comparable<E>> {

    private final class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    // 根节点
    private Node root;

    // 二分搜索树的大小
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 看二分搜索树中是否包含元素e
    public boolean contains(E e){
        return contains(root, e);
    }

    // 看以node为根的二分搜索树中是否包含元素e, 递归算法
    private boolean contains(Node node, E e){

        if(node == null)
            return false;

        if(e.compareTo(node.e) == 0)
            return true;
        else if(e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else // e.compareTo(node.e) > 0
            return contains(node.right, e);
    }

    // 向二分搜索树中添加元素e
    public void add(E e){
        root = add(e,root);
    }

    // 向以node为根的二分搜索树中插入元素e，递归算法，返回插入新节点后二分搜索树的根
    private Node add(E e, Node node) {
        if (node == null) {
            size ++ ;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0)
            node.left = add(e,node.left);
        if (e.compareTo(node.e) > 0)
            node.right = add(e,node.right);
        return node;
    }

    // 从二分搜索树中删除元素为e的节点
    public void remove(E e){
        root = remove(root, e);
    }

    // 删除掉以node为根的二分搜索树中值为e的节点, 递归算法，返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e) {
        if (node == null)
            return null;
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        }else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right,e);
            return node;
        }else {
            // 待删除左节点为空的情况
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 待删除的右节点为空的情况
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 待删除的左右节点都不为空的情况
            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点，或则删除左子树的最大节点
            // 用这个节点顶替待删除节点的位置
            // 方法1：即待删除节点右子树的最小节点
            /*Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            // Help GC
            node.left = node.right = null;
            return successor;*/
            // 方法二：删除左子树的最大节点
            Node successor = maxmum(node.left);
            successor.left = removeMax(node.left);
            node.left = node.right = null;
            return successor;
        }
    }

    // 从二分搜索树中删除最大值所在节点
    private Node removeMax(Node node) {
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node maxmum(Node node) {
        if (node.right == null)
            return node;
        return  maxmum(node.right);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    @Override
    public String toString() {
        // 借助双栈来打印树结构
        StringBuilder res = new StringBuilder();
        Stack globalStack =new Stack();
        if (root == null) return null;
        globalStack.push(root);
        int nBlank=32;
        boolean isRowEmpty=false;
        while (!isRowEmpty){
            Stack localStack=new Stack();
            isRowEmpty=true;
            for (int j=0; j < nBlank; j++)
                res.append(" ");
            while (!globalStack.isEmpty()){
                // 里面的while循环用于查看全局的栈是否为空
                Node temp = (Node)globalStack.pop();
                if (temp != null){
                    res.append(temp.e+" ");
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    //如果当前的节点下面还有子节点，则必须要进行下一层的循环
                    if (temp.left != null || temp.right != null)
                        isRowEmpty=false;
                }else {
                    //如果全局的栈则为空
                    res.append("  ");
                    localStack.push(null);
                    localStack.push(null);
                }
                // 相同层之间的间距
                for (int j=0; j < nBlank*2; j++){
                    res.append(" ");
                }
            }
            res.append("\n\n");
            nBlank/=2;
            //这个while循环用来判断，local栈是否为空,不为空的话，则取出来放入全局栈中
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop());
        }
        return res.toString();
    }
}
