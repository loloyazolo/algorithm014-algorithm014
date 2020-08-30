package week3;

import java.util.HashMap;
import java.util.Map;

//保证文件名唯一
public class GetFolderNames {

    public static void main(String[] args) {
//        String[] names = {"kaido","kaido(1)","kaido","kaido(1)"};
        String[] names = {"kaido","kaido(1)","kaido","kaido(1)"};
        GetFolderNames a = new GetFolderNames();
        String[] res = a.getFolderNames(names);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i] + ",");
        }
    }

    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        String[] res = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            if (!map.containsKey(names[i])) {
                res[i] = names[i];
                map.put(names[i], 1);
            } else {
                int count = map.get(names[i]);
                while (map.containsKey(names[i] + "(" + (count) + ")")){
                    count++;
                }
                res[i] = names[i] + "(" + (count) + ")";
                map.put(names[i] + "(" + (count) + ")", 0);
                map.put(names[i], map.get(names[i]) + 1);
            }
        }
        return res;
    }

}
