package week2;

import java.util.ArrayList;
import java.util.List;

public class JudgePoint24 {
    public static void main(String[] args) {
        JudgePoint24 judgePoint24 = new JudgePoint24();
        int[] nums = {4, 1, 8, 7};
        System.out.println(judgePoint24.judgePoint24(nums));
    }
    private int TARGET = 24;
    private double EPSILON = 1e-6;
    private int ADD= 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add((double) nums[i]);
        }
        return solve(list);
    }

    private boolean solve(List<Double> list) {
        int n = list.size();
        if(n == 0){
            return false;
        }
        //如果两数之差小于 EPSILON，则判断两数相等（浮点类型数据）
        if(n == 1){
            return Math.abs(list.get(0) - TARGET) < EPSILON;
         }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i != j){
                    List<Double> list2 = new ArrayList<>();
                    //先从数组中随机取出两个数
                    for (int k = 0; k < n; k++) {
                        if(k != i && k != j){
                            list2.add(list.get(k));
                        }
                    }
                    //随机取出一个运算符
                    for (int k = 0; k < 4; k++) {
                        //如果是加法活乘法，则满足交换律,不需要重复计算
                        if(k < 2 && i > j){
                            continue;
                        }
                        if(k == ADD){
                            list2.add(list.get(i) + list.get(j));
                        }else if(k == MULTIPLY){
                            list2.add(list.get(i) * list.get(j));
                        }else if(k == SUBTRACT){
                            list2.add(list.get(i) - list.get(j));
                        }else {
                            //如果除数为0，则跳过
                            if(Math.abs(list.get(j)) < EPSILON){
                                continue;
                            }
                            list2.add(list.get(i) / list.get(j));
                        }
                        if(solve(list2)){
                            return true;
                        }
                        list2.remove(list2.size() - 1);
                    }

                }

            }
        }
        return false;
    }
}
