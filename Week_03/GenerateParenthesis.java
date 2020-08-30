package week3;

import java.util.ArrayList;
import java.util.List;

//括号生成
public class GenerateParenthesis {

    public static void main(String[] args) {
        GenerateParenthesis g = new GenerateParenthesis();
        System.out.println(g.generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(res, n, n, "");
        return res;
    }

    private void dfs(List<String> res, int left, int right, String str) {
        if (left == right && right == 0) {
            res.add(str);
            return;
        }
        //只要有左括号就可以添加
        if (left > 0) {
            dfs(res, left - 1, right, str + "(");
        }
        //必须先加了左括号才能加右括号
        if (right > left) {
            dfs(res, left, right - 1, str + ")");
        }

    }
}
