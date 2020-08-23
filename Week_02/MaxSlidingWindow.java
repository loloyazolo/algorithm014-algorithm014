package week2;

import java.util.Deque;
import java.util.LinkedList;

public class MaxSlidingWindow {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] res = maxSlidingWindow2(nums, 3);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + ",");
        }
    }

    //双端队列
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        res[0] = deque.peekFirst();
        for (int i = k; i < n; i++) {
            if (deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();//保证deque递减
            }
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }


    //双指针求解
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return new int[]{};
        }
        int[] res = new int[n - k + 1];
        int left = 0, right = k - 1;
        int max = -1;
        int index = 0;
        while (right < n) {
            if (max < left) {
                max = left;
                for (int i = left + 1; i <= right; i++) {
                    if (nums[i] >= nums[max]) {
                        max = i;
                    }
                }
                res[index] = nums[max];
            }
            left++;
            right++;
            index++;
            if (right > n - 1) {
                return res;
            }
            if (nums[right] > nums[max]) {
                max = right;
            }
            res[index] = nums[max];
        }
        return res;
    }

}
