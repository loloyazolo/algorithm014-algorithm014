package week3;

//超级次方
public class SuperPow {
    int base = 1337;

    public static void main(String[] args) {
        SuperPow superPow = new SuperPow();
        int a = 2;
        int[] b = {};
        System.out.println(superPow.superPow2(a, b));
    }

    public int superPow2(int a, int[] b){
        if(a == 0 || b.length == 0){
            return 0;
        }
        return helper(a, b, b.length - 1);
    }

    private int helper(int a, int[] b, int index) {
        if(index == 0){
            return pow(a, b[index]);
        }
        int x = pow(a, b[index]);
        int y = pow(helper(a, b, index - 1), 10);
        return x * y % base;
    }

    private int pow(int a, int p){
        if(p == 0){
            return 1;
        }
        int res = 1;
        for (int i = 0; i < p; i++) {
            res = (res % base) * (a % base);
        }
        return res % 1337;
    }


    public int superPow(int a, int[] b) {
        if (b.length == 0) {
            return 1;
        }
        int last = b[b.length - 1];
        int[] nb = new int[b.length - 1];
        System.arraycopy(b, 0, nb, 0, b.length - 1);
        int sum = getMod(a, last);
        int left = getMod(superPow(a, nb), 10);
        return (sum * left) % base;
    }

    public int getMod(int a, int k) {
        int res = 1;
        a %= base;
        for (int i = 0; i < k; i++) {
            res *= a;
            res %= base;
        }
        return res;
    }
}
