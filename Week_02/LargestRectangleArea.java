package week2;

import java.util.Arrays;
import java.util.Stack;

//柱状图中最大矩形的面积
public class LargestRectangleArea {
    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 6, 2, 3};
        LargestRectangleArea a = new LargestRectangleArea();
        int res = a.largestRectangleArea2(nums);
        System.out.println(res);
    }

    public int largestRectangleArea(int[] heights) {
        int res = 0;
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }
        return res;
    }


    public int largestRectangleArea2(int[] heights) {
        int res = 0, n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                right[stack.pop()] = i;
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            res = Math.max((right[i] - left[i] - 1) * heights[i], res);
        }
        return res;
    }
}
