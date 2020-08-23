package week2;

public class CountSubstrings {
    public static void main(String[] args) {
        String str = "aaa";
        int res = countSubstrings(str);
        System.out.println(res);
    }

    public static int countSubstrings(String s) {
        int res = 0;
        int n = s.length();
        for(int i = 0; i < n; i++){
            int left = i, right = i;
            while(left >= 0 && right < n){
                if(s.charAt(left) == s.charAt(right)){
                    res++;
                    left--;
                    right++;
                }else{
                    break;
                }
            }
            left = i;
            right = i + 1;
            while(left >= 0 && right < n){
                if(s.charAt(left) == s.charAt(right)){
                    res++;
                    left--;
                    right++;
                }else{
                    break;
                }
            }
        }
        return res;
    }
}
