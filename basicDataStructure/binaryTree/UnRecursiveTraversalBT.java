package basicDataStructure.binaryTree;

import tool.BtNode;
import tool.Node;

import java.util.Stack;

public class UnRecursiveTraversalBT {

    public static void pre(BtNode head) {
        if (head == null) {
            return;
        }
        Stack<BtNode> stack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()) {
            BtNode node = stack.pop();
            System.out.println(node.value);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public static void in(BtNode head) {
        if (head == null) {
            return;
        }
        Stack<BtNode> stack = new Stack<>();
        while (head != null || !stack.isEmpty()) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                BtNode node = stack.pop();
                System.out.println(node.value);
                head = node.right;
            }
        }
    }

    public static void post1(BtNode head) {
        if (head == null) {
            return;
        }
        Stack<BtNode> stack1 = new Stack<>();
        Stack<BtNode> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            BtNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().value);
        }
    }

    public static void post2(BtNode head){
        if (head == null){
            return;
        }
        Stack<BtNode> stack = new Stack<>();
        BtNode c = null;
        stack.push(head);
        while(!stack.isEmpty()){
            c = stack.peek();
            if (c.left != null && c.left != head && c.right != head){
                stack.push(c.left);
            } else if (c.right != null && c.right != head) {
                stack.push(c.right);
            } else {
                head = stack.pop();
                System.out.println(head.value);
            }
        }
    }

    public static void test() {
        BtNode n0 = new BtNode(1);
        BtNode n1 = new BtNode(2);
        BtNode n2 = new BtNode(3);
        BtNode n3 = new BtNode(4);
        BtNode n4 = new BtNode(5);
        BtNode n5 = new BtNode(6);
        BtNode n6 = new BtNode(7);
        BtNode n7 = new BtNode(8);
        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;
        n3.left = n7;
//        pre(n0);
//        in(n0);
//        post1(n0);
        post2(n0);
    }

    public static void main(String[] args) {
        test();
    }

}
