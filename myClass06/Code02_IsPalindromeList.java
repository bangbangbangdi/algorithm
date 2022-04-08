package myClass06;


import tool.Node;
import tool.Tools;

import java.util.Stack;

public class Code02_IsPalindromeList {
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.value != stack.pop().value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node cur = head.next;
        Node right = head.next;
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }
        cur = head;
        while (!stack.isEmpty()) {
            if (cur.value != stack.pop().value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    public static boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node midUp = head;
        Node right = head;
        // find midUp Node
        while (right.next != null && right.next.next != null) {
            midUp = midUp.next;
            right = right.next.next;
        }
        Node pre = midUp;
        Node cur = midUp.next;
        Node next = null;

        //right part convert
        midUp.next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // check palindrome
        cur = pre;
        boolean res = true;
        while (cur != null && head != null) {
            if (head.value != cur.value) {
                res = false;
                break;
            }
            head = head.next;
            cur = cur.next;
        }

        //  recover list
        cur = pre.next;
        pre.next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return res;
    }


    public static void main(String[] args) {
        int testTimes = 500000;
        int maxSize = 5;
        int maxValue = 2;
        boolean succeed = true;
        for (int i = 0; (i < testTimes) && succeed; i++) {
            Node[] arr = Tools.generateRandomNodeArray(maxSize, maxValue);
            Node head = Tools.createNodes(arr);
            boolean res1 = isPalindrome1(head);
            boolean res2 = isPalindrome2(head);
            boolean res3 = isPalindrome3(head);
            if (res1 != res2 || res2 != res3) {
                succeed = false;
                Tools.printAllNodes(head);
                System.out.println("res1:" + res1 + " res2:" + res2 + " res3:" + res3);
            }
        }
        System.out.println(succeed ? "Nice" : "F");


//        Node node1 = new Node(1);
//        Node node2 = new Node(2);
//        Node node3 = new Node(3);
//        Node node4 = new Node(2);
//        Node node5 = new Node(1);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        System.out.println(isPalindrome1(node1));
//        System.out.println(isPalindrome2(node1));
//        Tools.printAllNodes(node1);
//        System.out.println(isPalindrome3(node1));
//        Tools.printAllNodes(node1);
    }
}
