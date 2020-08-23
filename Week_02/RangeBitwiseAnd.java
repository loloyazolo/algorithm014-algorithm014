package week2;

public class RangeBitwiseAnd {
    public static void main(String[] args) {
        int m = 1, n = 7;
        RangeBitwiseAnd rangeBitwiseAnd = new RangeBitwiseAnd();
        System.out.println(rangeBitwiseAnd.rangeBitwiseAnd(m, n));
    }

    //两个数同时右移
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        //不停将两个数右移，直到两数相等，等到两数的公共前缀
        while (m < n) {
            m >>= 1;
            n >>= 1;
            shift++;
        }
        return m << shift;
    }

    //n & (n - 1)会把最右边的1都置为0
    public int rangeBitwiseAnd2(int m, int n) {
        while (m < n) {
            n = n & (n - 1);
        }
        return n;
    }

}
