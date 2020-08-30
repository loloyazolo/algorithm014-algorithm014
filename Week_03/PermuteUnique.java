package week3;

import java.util.*;

// 全排列，数组中含重复数字
public class PermuteUnique {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        PermuteUnique p = new PermuteUnique();
        System.out.println(p.permuteUnique2(nums));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        Deque<Integer> path = new ArrayDeque<>();
        dfs(res, path, nums, used, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, Deque<Integer> path, int[] nums, boolean[] used, int depth) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i])
                continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
                continue;

            path.addFirst(nums[i]);
            used[i] = true;
            dfs(res, path, nums, used, depth + 1);
            used[i] = false;
            path.removeFirst();
        }
    }


    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        helper(res, nums, path, used);
        return res;
    }

    private void helper(List<List<Integer>> res, int[] nums, List<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            if(used[i]) continue;
            path.add(nums[i]);
            used[i] = true;
            helper(res, nums, path, used);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
