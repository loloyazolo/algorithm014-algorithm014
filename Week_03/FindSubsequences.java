package week3;

import java.util.ArrayList;
import java.util.List;

//递增子序列
public class FindSubsequences {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public static void main(String[] args) {
        FindSubsequences a = new FindSubsequences();
        int[] nums = {4, 6, 7, 7};
        System.out.println(a.findSubsequences(nums));
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(0, Integer.MIN_VALUE, nums);
        return res;
    }

    private void dfs(int cur, int last, int[] nums) {
        if (cur == nums.length) {
            if (temp.size() > 1) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        if (nums[cur] >= last) {
            temp.add(nums[cur]);
            dfs(cur + 1, nums[cur], nums);
            temp.remove(temp.size() - 1);
        }
        if (nums[cur] != last) {
            dfs(cur + 1, last, nums);
        }
    }
}
