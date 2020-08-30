package week3;

//第 k 个数
public class GetKthMagicNumber {

    public static void main(String[] args) {
        GetKthMagicNumber g = new GetKthMagicNumber();
        System.out.println(g.getKthMagicNumber(251));
    }

    public int getKthMagicNumber(int k) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[k];
        dp[0] = 1;
        for (int i = 1; i < k; i++) {
            int temp = Math.min(Math.min(dp[a] * 3, dp[b] * 5), dp[c] * 7);
            dp[i] = temp;
            if (dp[i] == dp[a] * 3) {
                a++;
            }
            if (dp[i] == dp[b] * 5) {
                b++;
            }
            if (dp[i] == dp[c] * 7) {
                c++;
            }
        }
        return dp[k - 1];
    }
}
