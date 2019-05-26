
/**
 * @author winter
 * @date 2019/5/26 17:16
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;

    public ArrayStack(int capacity){
        array = new Array<>();
    }

    public ArrayStack(){
        array = new Array<>();
    }
    @Override
    public int getSize() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int search(E e) {
        return array.find(e);
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for(int i = 0 ; i < array.size() ; i ++){
            res.append(array.get(i));
            if(i != array.size() - 1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }
}
