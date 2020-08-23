package week2;

import java.util.Stack;

public class DailyTemperatures {

    public static void main(String[] args) {
        int[] te = {73, 74, 75, 71, 69, 72, 76, 73};
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] res = dailyTemperatures.dailyTemperatures(te);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + ",");
        }
    }

    public int[] dailyTemperatures(int[] te) {
        int n = te.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++){
            while (!stack.isEmpty() && te[stack.peek()] < te[i]){
                int preIndex = stack.pop();
                res[preIndex] = i - preIndex;
            }
            stack.push(i);
        }
        return res;
    }
}
