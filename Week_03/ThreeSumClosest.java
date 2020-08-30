package week3;

import java.util.Arrays;

//最接近的三数之和
public class ThreeSumClosest {
    public static void main(String[] args) {
        ThreeSumClosest threeSumClosest = new ThreeSumClosest();
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        System.out.println(threeSumClosest.threeSumClosest(nums, target));
    }

    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        int res = 0;
        int min = 1000;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                int temp = Math.abs(sum - target);
                if (temp < min) {
                    min = temp;
                    res = sum;
                }
                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    return sum;
                }
            }
        }
        return res;
    }
}
