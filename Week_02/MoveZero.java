package week2;

public class MoveZero {
    public static void main(String[] args) {
        int[] nums = {1};
        MoveZero moveZero = new MoveZero();
        moveZero.moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i] + ",");
        }
    }

    public void moveZeroes(int[] nums) {
        int i = 0;
        int n = nums.length;
        for (int j = 0; j < n; j++) {
            if (nums[j] != 0) {
                nums[i] = nums[j];
                if(i != j )
                    nums[j] = 0;
                i++;
            }
        }
    }
}
