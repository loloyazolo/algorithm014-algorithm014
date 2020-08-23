package week2;

import java.util.*;


public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        List<List<String>> res = groupAnagrams.groupAnagrams(strs);
        System.out.println(res);
    }

    private List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if(strs.length == 0){
            return res;
        }
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int num : count) {
                sb.append(num);
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        for (List<String> list : map.values()) {
            res.add(list);
        }
        return res;
    }


}
