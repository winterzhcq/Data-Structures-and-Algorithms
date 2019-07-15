

/**
 * @author winter
 * @date 2019/7/15 21:04
 */
public class Main {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < 100  ; i++) {
            hashMap.add(i,i);
        }
        hashMap.remove(99);
        System.out.println(hashMap.getSize());
        hashMap.set(1,1111);
        System.out.println(hashMap.get(1));

//        for (int i = 0; i < 100 ; i++) {
//            System.out.println("key is " + i + ", value is "+hashMap.get(i));
//        }

    }
}
