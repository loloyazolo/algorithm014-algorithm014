package week2;

public class IsAnagram {
    public static void main(String[] args) {
        String s = "cod";
        String t = "jar";
        IsAnagram isAnagram = new IsAnagram();
        System.out.println(isAnagram.isAnagram(s, t));
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            count[c - 'a']--;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

}

