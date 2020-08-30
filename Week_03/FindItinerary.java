package week3;

import java.util.*;

//重新安排行程
public class FindItinerary {

    public static void main(String[] args) {

    }

    Map<String, PriorityQueue<String>> map = new HashMap<>();
    List<String> res = new ArrayList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0);
            String dst = ticket.get(1);
            if (!map.containsKey(src)) {
                map.put(src, new PriorityQueue<>());
            }
            map.get(src).offer(dst);
        }
        dfs("JFK");
        Collections.reverse(res);
        return res;
    }

    private void dfs(String cur) {
        while (map.containsKey(cur) && !map.get(cur).isEmpty()) {
            String temp = map.get(cur).poll();
            dfs(temp);
        }
        res.add(cur);
    }
}
