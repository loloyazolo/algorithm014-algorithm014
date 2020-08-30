package week3;

import java.util.ArrayList;
import java.util.List;

//全排列
public class Permute {

    public static void main(String[] args) {
        int[] nums = {1, 1, 3};
        Permute p = new Permute();
        System.out.println(p.permute(nums));
    }


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        int[] count = new int[nums.length];
        dfs(res, tempList, nums, count);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> tempList, int[] nums, int[] count) {
        if (tempList.size() == nums.length) {
            res.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (count[i] == 0) {
                count[i] = 1;
                tempList.add(nums[i]);
                dfs(res, tempList, nums, count);
                tempList.remove(tempList.size() - 1);
                count[i] = 0;
            }
        }
    }
}
