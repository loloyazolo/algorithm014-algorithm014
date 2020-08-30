package week3;

import structure.TreeNode;

import java.util.HashMap;
import java.util.Map;

//从前序与中序遍历序列构造二叉树
public class BuildTree {

    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return dfs(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int pre_left, int pre_right, int in_left, int in_right) {
        if(pre_left > pre_right){
            return null;
        }

        //前序遍历的第一个节点就是根节点
        int pre_root = pre_left;

        //找到根节点在中序遍历的位置
        int in_root = map.get(preorder[pre_root]);
        //建立根节点
        TreeNode root = new TreeNode(preorder[pre_root]);

        //获取左子树的数量
        int left_subtree_size = in_root - in_left;

        //递归建立左子树
        // 先序遍历中「从 左边界+1 开始的 left_subtree_size」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = dfs(preorder, inorder, pre_left + 1, pre_left + left_subtree_size, in_left, in_root - 1);

        //递归建立右子树
        root.right = dfs(preorder, inorder, pre_left + 1 + left_subtree_size, pre_right, in_root + 1, in_right);
        return root;
    }
}
