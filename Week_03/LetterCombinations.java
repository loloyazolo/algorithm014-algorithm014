package week3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//电话号码的字母组合
public class LetterCombinations {
    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        String s = "23";
        List<String> res = letterCombinations.letterCombinations(s);
        System.out.println(res);
    }

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits.length() == 0){
            return new ArrayList<>();
        }
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        StringBuilder path = new StringBuilder();
        dfs(res, path, digits, map);
        return res;
    }

    private void dfs(List<String> res, StringBuilder path, String digits, Map<Character, String> map) {
        if (digits.length() == path.length()) {
            res.add(path.toString());
            return;
        }
        int i = path.length();
        char c = digits.charAt(i);
        String letters = map.get(c);
        for (char l : letters.toCharArray()) {
            path.append(l);
            dfs(res, path, digits, map);
            path.delete(path.length() - 1, path.length());
        }
    }


}
