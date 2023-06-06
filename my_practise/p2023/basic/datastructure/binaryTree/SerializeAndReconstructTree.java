package my_practise.p2023.basic.datastructure.binaryTree;

import tool.BtNode;
import tool.Tools;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.binaryTree
 * className:      SerializeAndReconstructTree
 * author:     BangDi
 * description:  序列化与反序列化
 * date:    2023/6/6 9:26
 * version:    1.0
 */
public class SerializeAndReconstructTree {

    public static Queue<Integer> preSerialize(BtNode node) {
        if (node == null) {
            return null;
        }
        Queue<Integer> queue = new LinkedList<>();
        pre(node, queue);
        return queue;
    }

    public static void pre(BtNode cur, Queue<Integer> queue) {
        if (cur == null) {
            queue.add(null);
            return;
        }
        queue.add(cur.value);
        pre(cur.left, queue);
        pre(cur.right, queue);
    }

    public static BtNode preReconstruct(Queue<Integer> queue) {
        if (queue == null || queue.peek() == null) {
            return null;
        }
        Integer headValue = queue.poll();
        BtNode head = new BtNode(headValue);
        preRec(head, queue);
        return head;
    }

    public static void preRec(BtNode head, Queue<Integer> queue) {
        Integer left = queue.poll();
        if (left != null) {
            BtNode leftSub = new BtNode(left);
            head.left = leftSub;
            preRec(leftSub, queue);
        }
        Integer right = queue.poll();
        if (right != null) {
            BtNode rightSub = new BtNode(right);
            head.right = rightSub;
            preRec(rightSub, queue);
        }
    }

    public static Stack<Integer> postSerialize(BtNode head) {
        if (head == null) {
            return new Stack<>();
        }
        Stack<Integer> stack = new Stack<>();
        postSer(head, stack);
        return stack;
    }

    public static void postSer(BtNode cur, Stack<Integer> stack) {
        if (cur == null) {
            stack.add(null);
            return;
        }
        postSer(cur.left, stack);
        postSer(cur.right, stack);
        stack.add(cur.value);
    }

    public static BtNode postReconstruct(Stack<Integer> stack) {
        if (stack == null || stack.peek() == null) {
            return null;
        }
        Integer value = stack.pop();
        BtNode head = new BtNode(value);
        postRec(stack, head);
        return head;
    }

    public static void postRec(Stack<Integer> stack, BtNode head) {
        Integer rightValue = stack.pop();
        if (rightValue != null) {
            BtNode right = new BtNode(rightValue);
            head.right = right;
            postRec(stack, right);
        }
        Integer leftValue = stack.pop();
        if (leftValue != null) {
            BtNode left = new BtNode(leftValue);
            head.left = left;
            postRec(stack, left);
        }
    }

    public static void test() {
        BtNode n1 = new BtNode(1);
        BtNode n2 = new BtNode(2);
        BtNode n3 = new BtNode(3);
        BtNode n4 = new BtNode(4);
        BtNode n5 = new BtNode(5);
        BtNode n6 = new BtNode(6);
        BtNode n7 = new BtNode(7);
        BtNode n8 = new BtNode(8);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n4.left = n8;
//        Queue<Integer> queue = preSerialize(n1);
        Stack<Integer> stack = postSerialize(n1);
//        while (!stack.isEmpty()) {
//            System.out.print(stack.pop() + "-");
//        }
        BtNode head = postReconstruct(stack);
        System.out.println();
        Tools.printBinaryTree(head);
    }

    public static void main(String[] args) {
        test();
    }

}
