package week3;

import java.util.ArrayList;
import java.util.List;

//不浪费原料的汉堡制作方案
public class NumOfBurgers {

    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> res = new ArrayList<>();
        int giant = 0, small = 0;
        if (tomatoSlices % 2 != 0) {
            return res;
        } else {
            giant = tomatoSlices / 2 - cheeseSlices;
            small = cheeseSlices - giant;
            if (giant < 0 || small < 0) {
                return res;
            }
            res.add(giant);
            res.add(small);
        }
        return res;
    }
}
