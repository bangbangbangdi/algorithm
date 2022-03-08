package myClass02;


public class Code01_ReverseList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            this.value = data;
        }

    }

    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;

        while (head != null) {
            next = head.next;

            head.next = pre;
            pre = head;

            head = next;
        }

        return pre;
    }

    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }


    public static Node addNodeList(Node head, int data) {
        if (head == null) {
            return new Node(data);
        }
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new Node(data);
        return head;
    }

    public static void printNode(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    public static void test() {
        Node node = addNodeList(null, 1);
        addNodeList(node, 2);
        addNodeList(node, 3);
        addNodeList(node, 4);
        printNode(node);
        Node reverseList = reverseLinkedList(node);
        System.out.println();
        printNode(reverseList);
    }

    public static void main(String[] agrs) {
        test();
    }
}