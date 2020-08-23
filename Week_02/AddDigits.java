package week2;

public class AddDigits {
    public static void main(String[] args) {
        AddDigits addDigits = new AddDigits();
        System.out.println(addDigits.addDigits2(38));
    }

    public int addDigits(int num) {
        if (num / 10 == 0) {
            return num;
        }
        num = num / 10 + num % 10;
        return addDigits(num);
    }

    //神奇的数学解法
    public int addDigits2(int num) {
        return (num - 1) % 9 + 1;
    }
}
