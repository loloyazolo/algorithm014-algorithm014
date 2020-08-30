package week3;

import structure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//二叉搜索树的第k大节点
public class KthLargest {
    public int kthLargest(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res.get(res.size() - k);
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if(null == root){
            return;
        }
        dfs(root.left, res);
        res.add(root.val);
        dfs(root.right, res);
    }

}
