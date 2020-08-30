package week3;

// 重新排列数组
public class Shuffle {

    public int[] shuffle(int[] nums, int n) {
        int[] res = new int[nums.length];
        int left = 0, right = n;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                res[i] = nums[left];
                left++;
            } else {
                res[i] = nums[right];
                right++;
            }
        }
        return res;
    }
}
