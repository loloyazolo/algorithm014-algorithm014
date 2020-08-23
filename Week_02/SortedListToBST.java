package week2;

import week2.structure.ListNode;
import week2.structure.TreeNode;

public class SortedListToBST {

    public static void main(String[] args) {
        ListNode head = new ListNode(-3);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(6);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        SortedListToBST s = new SortedListToBST();
        TreeNode treeNode = s.sortedListToBST2(head);
        System.out.println(treeNode.val);
    }

    //中序遍历建树
    ListNode globalNode;

    private TreeNode sortedListToBST2(ListNode head) {
        globalNode = head;
        int length = getLength(head);
        return buildTree2(0, length - 1);
    }
    private int getLength(ListNode head) {
        int res = 0;
        while (head != null) {
            res++;
            head = head.next;
        }
        return res;
    }

    private TreeNode buildTree2(int left, int right) {
        if(left > right){
            return null;
        }
        int mid = (left + right + 1)/2;
        TreeNode root = new TreeNode();
        root.left = buildTree2(left, mid - 1);
        root.val = globalNode.val;
        globalNode = globalNode.next;
        root.right = buildTree2(mid + 1, right);
        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head, null);
    }

    //
    private TreeNode buildTree(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode min_val = getMedian(left, right);
        TreeNode root = new TreeNode(min_val.val);
        root.left = buildTree(left, min_val);
        root.right = buildTree(min_val.next, right);
        return root;
    }

    private ListNode getMedian(ListNode left, ListNode right) {
        ListNode slow = left;
        ListNode fast = left;
        while (fast != right && fast.next != right) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
