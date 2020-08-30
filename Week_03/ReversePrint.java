package week3;

import structure.ListNode;

import java.util.Stack;

//从尾到头打印链表
public class ReversePrint {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        node.next = node1;
        node1.next = node2;
        ReversePrint s = new ReversePrint();
        int[] res = s.reversePrint2(node);
        System.out.println(res);
    }

    //栈
    public int[] reversePrint(ListNode head) {
        if(null == head){
            return new int[]{};
        }
        Stack<Integer> stack = new Stack<>();
        int n = 0;
        while(head != null){
            stack.push(head.val);
            head = head.next;
            n++;
        }
        int[] res = new int[n];
        int index = 0;
        while(!stack.isEmpty()){
            res[index] = stack.pop();
            index++;
        }
        return res;

    }

    public int[] reversePrint2(ListNode head){
        int len = 0;
        ListNode cur = head;
        while(cur != null){
            cur = cur.next;
            len++;
        }
        int[] res = new int[len];
        for(int i = len - 1; i >= 0; i--){
            res[i] = head.val;
            head = head.next;
        }
        return res;
    }
}
