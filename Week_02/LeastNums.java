package week2;

import java.util.PriorityQueue;

public class LeastNums {
    public static void main(String[] args) {
        LeastNums leastNums = new LeastNums();
        int[] arr = {3, 2, 1, 4, 7, 9};
        int k = 5;
        int[] res = leastNums.getLeastNumbers(arr, k);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        return res;
    }
}
