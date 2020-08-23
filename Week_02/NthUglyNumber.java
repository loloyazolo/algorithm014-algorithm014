package week2;

import java.util.PriorityQueue;

//丑数
public class NthUglyNumber {
    public static void main(String[] args) {
        NthUglyNumber nthUglyNumber = new NthUglyNumber();

        System.out.println(nthUglyNumber.nthUglyNumber2(10));
    }

    //动态规划
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        int a = 0, b = 0, c = 0;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(Math.min(2 * dp[a], 3 * dp[b]), 5 * dp[c]);
            if(dp[i] == 2 * dp[a])
                a++;
            if(dp[i] == 3 * dp[b])
                b++;
            if(dp[i] == 5 * dp[c])
                c++;
        }
        return dp[n - 1];
    }

    //小根堆解法， 时间复杂度较高
    public int nthUglyNumber2(int n){
        int[] uglyNums = {2, 3, 5};
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.add(1L);
        int count = 0;//记录弹出的数的个数
        while(true){
            long cut = pq.poll();

            count++;
            if(count >= n){
                return (int)cut;
            }
            for (int i = 0; i < uglyNums.length; i++) {
                if(!pq.contains(uglyNums[i] * cut)){
                    pq.add(uglyNums[i] * cut);
                }
            }
        }
    }
}
