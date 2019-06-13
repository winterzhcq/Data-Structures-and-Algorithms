
/**
 * @author winter
 * @date 2019/6/13 18:42
 */
public class Main {
    public static void main(String[] args) {
        SkipList<Integer> skipList = new SkipList<>();
        for (int i = 0; i < 100; i++) {
            skipList.add(i);
        }
        System.out.println(skipList);
        skipList.remove(50);
        System.out.println(skipList);
        System.out.println(skipList.get(20));
    }
}
