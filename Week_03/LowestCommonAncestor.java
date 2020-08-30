package week3;

import structure.TreeNode;

//二叉树的最近公共祖先
public class LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(1);
        TreeNode node7 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node4.left = node5;
        node4.right = node6;
        node2.left = node7;
        node2.right = node8;
        LowestCommonAncestor l = new LowestCommonAncestor();
        System.out.println(l.lowestCommonAncestor(root, node7, node8).val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //如果越过叶子节点，或者已经找到p或者q
        if(root == null || root == p || root == q){
            return root;
        }
        //先遍历左节点
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null)
            return right;
        if(right == null)
            return left;
        return root;
    }
}
