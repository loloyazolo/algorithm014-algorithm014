package week3;

import structure.TreeNode;

import java.util.ArrayList;
import java.util.List;
//中序遍历
public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res, root);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode root) {
        if(root == null){
            return;
        }
        dfs(res, root.left);
        res.add(root.val);
        dfs(res, root.right);
    }
}
