
public class Main {

    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = 0 ; i < 5 ; i ++){
            //linkedList.addFirst(i);
            linkedList.addLast(i);
            System.out.println(linkedList);
        }

        linkedList.addLast( 666);
        System.out.println(linkedList);

        linkedList.remove(0);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);

        linkedList.remove(0);
        System.out.println(linkedList);

        linkedList.remove(0);
        System.out.println(linkedList);

        linkedList.remove(0);
        System.out.println(linkedList);

//        linkedList.remove(0);
//        System.out.println(linkedList);

        linkedList.add(0,333);
        System.out.println(linkedList);
    }
}
