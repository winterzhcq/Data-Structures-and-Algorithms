

/**²âÊÔÆ½ºâ¶þ²æÊ÷
 * @author winter
 * @date 2019/6/15 19:44
 */
public class Main {

    public static void main(String[] args) {

        AVLTree<Integer,Integer> avlTree = new AVLTree<>();
        avlTree.add(10,10);
        avlTree.add(15,15);
        avlTree.add(9,9);
        avlTree.add(12,12);
        avlTree.add(6,6);
        avlTree.add(7,7);
        avlTree.add(13,13);
        avlTree.add(20,20);
        avlTree.add(1,1);
        avlTree.add(5,5);
        System.out.println(avlTree.isBalanced());
        System.out.println(avlTree);
        avlTree.remove(1);
        System.out.println(avlTree);
        System.out.println(avlTree.isBalanced());
        System.out.println(avlTree.isBST());
    }
}
