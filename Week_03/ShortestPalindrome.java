package week3;

//最短回文串
public class ShortestPalindrome {
    public static void main(String[] args) {
        ShortestPalindrome s = new ShortestPalindrome();
        String str = "aacecaaa";
        System.out.println(s.shortestPalindrome(str));
    }

    //该方法会超时
    public String shortestPalindrome(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (right > left) {
            if (s.charAt(right) == s.charAt(left)) {
                int R = right, L = left;
                while(R > L && s.charAt(R) == s.charAt(L)){
                    R--;
                    L++;
                }
                if(R == L || (Math.abs(R - L) == 1 && s.charAt(R) == s.charAt(L))){
                    break;
                }
            }

            right--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i > right; i--) {
            sb.append(s.charAt(i));
        }
        sb.append(s);
        return sb.toString();
    }
}
