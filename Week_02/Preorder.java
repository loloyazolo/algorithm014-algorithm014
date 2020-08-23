package week2;

import week2.structure.Node;

import java.util.ArrayList;
import java.util.List;

public class Preorder {

    public static void main(String[] args) {
        System.out.println();
    }
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(Node root, List<Integer> res) {
        if(root == null){
            return;
        }
        res.add(root.val);
        for(Node node : root.children){
            dfs(node, res);
        }
    }
}
