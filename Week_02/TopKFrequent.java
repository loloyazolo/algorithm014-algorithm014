package week2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequent {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        TopKFrequent topKFrequent = new TopKFrequent();
        int[] res = topKFrequent.topKFrequent(nums, k);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i] + ",");
        }
    }

    //大顶堆解法
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        //堆中存一个数组，第一位是数字，第二位是频次，根据频次排序
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] nums1, int[] nums2) {
                return nums2[1] - nums1[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }
        int i = 0;
        while (i < k) {
            res[i] = pq.poll()[0];
            i++;
        }
        return res;
    }
}
