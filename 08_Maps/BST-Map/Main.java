


/**
 * @author winter
 * @date 2019/7/14 15:24
 */
public class Main {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new BSTMap<>();
        for (int i = 0; i < 10  ; i++) {
            map.add(i,i);
        }
        System.out.println(map);
        System.out.println(map.contains(2));
        System.out.println(map.get(3));
        System.out.println(map.remove(5));
        System.out.println(map.getSize());
    }
}
