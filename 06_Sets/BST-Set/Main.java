package cn.winter.datastructures.set.treeset;

import java.util.Random;

/**
 * @author winter
 * @date 2019/6/19 20:16
 */
public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Set<Integer> set = new BSTSet<>();
        for (int i = 0; i < 10 ; i++) {
            set.add(10);
            set.add(20);
            set.add(15);
            set.add(3);
            set.add(18);
            set.add(25);
            set.add(8);
            set.add(8);
        }
        //set.remove(8);
        System.out.println(set.contains(8));
        System.out.println(set);
    }
}
