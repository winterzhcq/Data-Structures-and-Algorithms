
/**
 * @author winter
 * @date 2019/11/3 15:48
 */
public class Main {
    public static void main(String[] args) {
        BM bm = new BM();
        final int bm1 = bm.bm("abc123dcec123d".toCharArray(), 14, "ec".toCharArray(), 2);
        System.out.println(bm1);

    }
}
