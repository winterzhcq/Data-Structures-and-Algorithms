
import java.util.Random;

/**
 * @author winter
 * @date 2019/6/15 19:44
 */
public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        BST<Integer> bst = new BST<>();
        bst.add(10);
        bst.add(15);
        bst.add(9);
        bst.add(12);
        bst.add(6);
        bst.add(7);
        bst.add(13);
        bst.add(20);
        bst.add(1);
        bst.add(5);

        System.out.println(bst);
//        bst.remove(1);
//        System.out.println(bst);
    }
}
