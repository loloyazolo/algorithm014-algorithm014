package week3;

import java.util.*;

public class FourSum {

    public static void main(String[] args) {
        FourSum f = new FourSum();
        int[] nums = {1, -2, -5, -4, -3, 3, 3, 5};
        int target = -11;
        System.out.println(f.fourSum2(nums, target));
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (n < 4) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; i++) {
            if (nums[i] > target) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int left = j + 1, right = n - 1;
                while (left < right) {
                    int cur = nums[i] + nums[j] + nums[left] + nums[right];
                    if (cur == target) {
                        res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right])));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (cur > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> fourSum1(int[] nums, int target) {
        Set<List<Integer>> res = new HashSet<>();
        int n = nums.length;
        if (n < 4) {
            return new ArrayList<>(res);
        }
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                int complement = target - nums[i] - nums[j];
                int left = j + 1, right = n - 1;
                while (right > left) {
                    if (nums[left] + nums[right] > complement) {
                        right--;
                    } else if (nums[left] + nums[right] < complement) {
                        left++;
                    } else {
                        res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right])));
                        left++;
                        right--;
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] > 0)
                break;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int left = i + 1, right = n - 1;
            while (left < right) {
                int cur = nums[i] + nums[left] + nums[right];
                if (cur > 0) {
                    right--;
                } else if (cur < 0) {
                    left++;
                } else {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;
                    left++;
                    right--;
                }
            }
        }
        return res;
    }


}
