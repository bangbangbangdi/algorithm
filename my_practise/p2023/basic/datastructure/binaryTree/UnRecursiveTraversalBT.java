package my_practise.p2023.basic.datastructure.binaryTree;

import tool.BtNode;

import java.util.Stack;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.binaryTree
 * className:      UnRecursiveTraversalBT
 * author:     BangDi
 * description:  不递归遍历二叉树
 * date:    2023/6/7 16:57
 * version:    1.0
 */
public class UnRecursiveTraversalBT {

    public static void pre(BtNode node) {
        if (node == null) {
            return;
        }
        Stack<BtNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            BtNode cur = stack.pop();
            System.out.print(cur.value + " ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    public static void in(BtNode node) {
        if (node == null) {
            return;
        }
        Stack<BtNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.print(node.value + " ");
                node = node.right;
            }
        }
    }

    public static void post(BtNode node) {
        if (node == null) {
            return;
        }
        Stack<BtNode> stack1 = new Stack<>();
        Stack<BtNode> stack2 = new Stack<>();
        stack1.push(node);
        while (!stack1.empty()) {
            node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().value + " ");
        }
    }

    public static void post2(BtNode node) {
        if (node == null) {
            return;
        }
        BtNode c = null;
        Stack<BtNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.empty()) {
            c = stack.peek();
            if (c.left != null && c.left != node && c.right != node) {
                stack.push(c.left);
            } else if (c.right != null && c.right != node) {
                stack.push(c.right);
            } else {
                node = stack.pop();
                System.out.print(node.value + " ");
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
        post(n0);
        System.out.println();
        post2(n0);
    }

    public static void main(String[] args) {
        test();
    }
}
