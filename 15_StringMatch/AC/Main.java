
/**
 * @author winter
 * @date 2019/11/7 21:46
 */
public class Main {
    public static void main(String[] args) {
        AC ac = new AC();
        ac.add("c");
        ac.add("bc");
        ac.add("bcd");
        ac.add("abcd");
        ac.buildFailurePointer();
        ac.match("sfcasagfsabcdadfhh");
    }
}
