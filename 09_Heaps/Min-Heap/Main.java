
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int n = 1000000;

        MinHeap<Integer> minHeap = new MinHeap<>();
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            minHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++)
            arr[i] = minHeap.extractMin();

        for(int i = 1 ; i < n ; i ++)
            if (arr[i - 1] > arr[i])
                throw new IllegalArgumentException("Error");

        System.out.println("Test MinHeap completed.");
    }
}
