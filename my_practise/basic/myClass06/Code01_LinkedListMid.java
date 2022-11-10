package my_practise.basic.myClass06;

import tool.Node;
import tool.Tools;

import java.util.ArrayList;

public class Code01_LinkedListMid {


    public static Node midOrUpMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node midOrDownMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node midOrUpMidPreNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node midOrDownMidPreNode(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node right1(Node head) {
        if (head == null) {
            return head;
        }
        ArrayList<Node> arr = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 1) / 2);
    }

    public static Node right2(Node head) {
        if (head == null) {
            return head;
        }
        ArrayList<Node> arr = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get(arr.size() / 2);
    }

    public static Node right3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 3) / 2);
    }

    public static Node right4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 2) / 2);
    }

    public static void main(String[] args) {
        int testTimes = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; (i < testTimes) && succeed; i++) {
            Node[] arr = Tools.generateRandomNodeArray(maxSize, maxValue);
            Node head = Tools.createNodes(arr);
            Node midOrUpMidNode = midOrUpMidNode(head);
            Node right1 = right1(head);
            Node midOrDownMidNode = midOrDownMidNode(head);
            Node right2 = right2(head);
            Node midOrUpMidPreNode = midOrUpMidPreNode(head);
            Node right3 = right3(head);
            Node midOrDownMidPreNode = midOrDownMidPreNode(head);
            Node right4 = right4(head);
            if (midOrUpMidNode != right1 || midOrDownMidNode != right2 || midOrUpMidPreNode != right3 || midOrDownMidPreNode != right4) {
                succeed = false;
                Tools.printAllNodes(head);
                System.out.println("midUp " + midOrUpMidNode.value + "  right1 " + right1.value);
                System.out.println("midDown " + midOrDownMidNode.value + "  right2 " + right2.value);
                System.out.println("midUpPre " + midOrUpMidPreNode.value + "  right3 " + right3.value);
                System.out.println("midDownPre " + midOrDownMidPreNode.value + "  right4 " + right4.value);
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }
}
