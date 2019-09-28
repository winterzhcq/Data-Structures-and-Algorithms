
/**
 * @author winter
 * @date 2019/9/28 9:41
 */
public class MinHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MinHeap(){
        data = new Array<>();
    }

    public MinHeap(int capacity){
        data = new Array<>(capacity);
    }

    // 返回堆中的元素
    public int size(){
        return data.size();
    }

    // 查看堆是否为null
    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，数组下标从0开始，一个索引所表示的元素的父亲节点的索引
    private int parent(int index){
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index-1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return index*2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return index*2 + 2;
    }

    // 向堆中添加元素
    public void add(E e){
        data.addLast(e);
        siftUp(data.size() - 1);
    }

    // 上浮操作
    private void siftUp(int i) {
        while (i > 0 && data.get(parent(i)).compareTo(data.get(i)) > 0){
            data.swap(i, parent(i));
            i = parent(i);
        }
    }

    // 看堆中的最小元素
    public E findMin(){
        if(data.size() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    // 取出堆顶元素
    // 取出堆中最小元素
    public E extractMin(){
        E ret = findMin();
        data.swap(0, data.size() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    // 下沉操作
    private void siftDown(int i) {
        while (leftChild(i) < data.size()){
            int j = leftChild(i);
            if (j+1 < data.size() && data.get(j+1).compareTo(data.get(j)) < 0)
                j++;// 记录孩子左右孩子节点的最小索引
            if (data.get(i).compareTo(data.get(j)) < 0)
                break;
            data.swap(i,j);
            i = j;
        }
    }
}
