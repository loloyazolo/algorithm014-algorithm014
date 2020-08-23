package week2;

import week2.structure.TreeNode;

public class MinDepth {
    public static void main(String[] args) {
        System.out.println();
    }

    public int minDepth(TreeNode root) {
        if(root.left == null && root.right == null){
            return 0;
        }
        if(root.left == null){
            return minDepth(root.right) + 1;
        }
        if(root.right == null)
            return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
