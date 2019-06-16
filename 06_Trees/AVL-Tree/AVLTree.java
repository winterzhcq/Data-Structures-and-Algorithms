
import java.util.ArrayList;
import java.util.Stack;

/**
 * 平衡二叉树是一种特殊的二分搜索树，平衡二叉树在二分搜索树的基础上定义为
 * 对于任意一个节点，左子树和右子树的高度差不能超过一，防止二分搜索树退化
 * 成链表，但是也需要我们添加一个节点来手动维护平衡二叉树的平衡性质,其中可
 * 以分为四种情况：插入的节点在不平衡节点的左侧的左侧，简称LL，我们需要一次
 * 右旋来保持树的平衡；如果插入的节点在不平衡节点的右侧的右侧，简称RR，我们
 * 需要一次左旋转来保持树的平衡；如果插入节点在不平衡节点的左侧节点的右节点
 * 处，简称LR，我们需要先让不平衡节点的左子树做一次左旋转，转换成LL的情况，
 * 然后再让该节点做一次右旋转即可；同理对于插入节点在不平衡节点右侧节点的
 * 左子节点生，简称RL，我们需要先让该节点的右子节点做一次右旋，转换成RR的
 * 情况，最后在对该节点做一次左旋即可。对于LL和RR是对称的情况，对于LR和RL
 * 也是对称的情况，删除操作也是先删除该节点，然后在维护一下新树的平衡性
 * @author winter
 * @date 2019/6/16 12:01
 */
public class AVLTree<K extends Comparable<K>,V> {

    private final class Node{
        public  K key;
        public V value;
        public Node left , right;
        public int height;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }
    // AVLTree的root节点
    private Node root;

    // 树的大小
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmppty(){
        return size == 0;
    }

    // 获取树节点的高度
    private int getHeight(Node node){
        if (node == null)
            return 0;
        return node.height;
    }

    // 获取节点node的平衡因子
    private int getBalanceFactor(Node node){
        return getHeight(node.left) - getHeight(node.right);
    }

    // 向平衡二叉树中添加元素
    public void add(K key, V value){
        root = add(root,key,value);
    }

    // 向以node为根的平衡二叉树中插入元素(key, value)，递归算法
    // 返回插入新节点后平衡二叉树的根
    private Node add(Node node, K key, V value) {
        if (node == null){
            size ++;
            return new Node(key,value);
        }
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left,key,value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right,key,value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        // 更新hight
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));

        // 计算平衡因子
        int balanceFactory = getBalanceFactor(node);

        // 维护平衡二叉树的平衡
        // LL 该树向左倾斜
        if (balanceFactory > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);
        // RR 该树向右倾斜
        if (balanceFactory < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);
        // LR 插入的节点在左边孩子的右孩子节点上，先进行坐旋转，变成LL的情况，然后再进行右旋转维护平衡性
        if (balanceFactory > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL 插入的节点在右孩子的左节点上，先进行右旋转，变成RR的情况，然后再进行左旋转维护平衡性
        if (balanceFactory < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    // 从平衡二叉树中删除键为key的节点
    public V remove(K key){
        Node node = getNode(root,key);
        if (node != null){
            root = remove(root,key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null)
            return null;
        Node retNode;
        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left,key);
            retNode = node;
        }else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right,key);
            retNode = node;
        }else {
            // key.compareTo(node.key) == 0
            // 先刪除再维护平衡
            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            }

            // 待删除节点右子树为空的情况
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            }

            // 待删除节点左右子树均不为空的情况
            else{
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }
        if(retNode == null)
            return null;

        // 更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);

        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);

        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }
    // 判断该二叉树是否是一棵平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }

    // 判断以Node为根的二叉树是否是一棵平衡二叉树，递归算法
    private boolean isBalanced(Node node){

        if(node == null)
            return true;

        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 判断该二叉树是否是一棵二分搜索树
    public boolean isBST(){

        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for(int i = 1 ; i < keys.size() ; i ++)
            if(keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys){

        if(node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }
    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    public V get(K key){
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }
    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node,K key) {
        if (node == null)
            return null;
        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转的过程
        x.right = y;
        y.left = T3;

        // 更新树的高度值
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) +1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) +1;
        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;

        // 向左旋转
        x.left = y;
        y.right = T2;

        // 更新树的高度值
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) +1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) +1;

        return x;
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
                    res.append(temp.value+" ");
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
