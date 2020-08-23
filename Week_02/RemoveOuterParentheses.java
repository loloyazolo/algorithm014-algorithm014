package week2;

import java.util.Stack;

public class RemoveOuterParentheses {
    public static void main(String[] args) {
        String s = "()()";
        String res = removeOuterParentheses2(s);
        System.out.println(res);
    }

    //栈
    public static String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> sc = new Stack<>();
        int start = 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                sc.push(')');
            } else {
                sc.pop();
            }
            if (sc.isEmpty()) {
                sb.append(s.substring(start, i));
                start = i + 2;
            }
        }
        return sb.toString();
    }

    public static String removeOuterParentheses2(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ')'){
                count--;
            }
            if(count > 0){
                sb.append(s.charAt(i));
            }
            if(s.charAt(i) == '('){
                count++;
            }
        }
        return sb.toString();
    }

}
