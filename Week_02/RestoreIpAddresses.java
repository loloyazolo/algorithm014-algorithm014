package week2;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {

    public static void main(String[] args) {
        String s = "1111";
        RestoreIpAddresses restoreIpAddresses = new RestoreIpAddresses();
        List<String> res = restoreIpAddresses.restoreIpAddresses2(s);
        System.out.println(res);
    }

    //暴力循环
    public List<String> restoreIpAddresses2(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int a = 1; a < 4; a++) {
            for (int b = 1; b < 4; b++) {
                for (int c = 1; c < 4; c++) {
                    for (int d = 1; d < 4; d++) {
                        if (a + b + c + d == s.length()) {
                            int n1 = Integer.parseInt(s.substring(0, a));
                            int n2 = Integer.parseInt(s.substring(a, a + b));
                            int n3 = Integer.parseInt(s.substring(a + b, a + b + c));
                            int n4 = Integer.parseInt(s.substring(a + b + c));
                            if (n1 < 256 && n2 < 256 && n3 < 256 && n4 < 256) {
                                sb.append(n1).append(".").append(n2).append(".").append(n3).append(".").append(n4);
                                if (sb.length() == s.length() + 3) {
                                    res.add(sb.toString());
                                }
                                sb = new StringBuilder();
                            }

                        }
                    }
                }
            }
        }
        return res;
    }

    //递归，回溯
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(res, s, new StringBuilder());
        return res;
    }

    public void dfs(List<String> res, String s, StringBuilder path) {
        if (path.toString().split("\\.").length > 4) {
            return;
        }
        if (s.length() == 0 && path.toString().split("\\.").length == 4) {
            res.add(path.substring(0, path.length() - 1));
            return;
        }
        for (int i = 1; i < s.length() + 1; i++) {
            String temp = s.substring(0, i);
            if (isValid(temp)) {
                if (Integer.valueOf(temp.substring(0, 1)) > 2 && temp.length() == 3) {
                    break;
                }
                path.append(temp).append(".");
                dfs(res, s.substring(i), path);
                path = path.delete(path.length() - i - 1, path.length());
            }
        }
    }

    boolean isValid(String str) {
        if (str.length() == 0 || str.length() > 3) {
            return false;
        }
        if (str.length() > 1 && str.startsWith("0")) {
            return false;
        }
        if (Integer.valueOf(str) > 255) {
            return false;
        }
        return true;
    }
}
