package week2;

public class Rob {
    public static void main(String[] args) {
        int[] nums = {1, 6, 2, 1, 5};
        System.out.println(rob(nums));
    }

    //动态规划
    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];
    }
}
