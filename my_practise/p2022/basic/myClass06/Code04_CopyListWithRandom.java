package my_practise.p2022.basic.myClass06;


import java.util.HashMap;

public class Code04_CopyListWithRandom {
    public static class Node {
        private int value;
        private Node next;
        private Node random;

        public Node(int v) {
            value = v;
        }
    }

    public static Node copyListWithRand1(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        Node next = null;
        Node copyNode = null;
        while (cur != null) {
            next = cur.next;

            copyNode = new Node(cur.value);
            cur.next = copyNode;
            copyNode.next = next;

            cur = next;
        }
        cur = head;
        while (cur != null) {
            copyNode = cur.next;
            next = cur.next.next;

            // 考虑random可能为空的情况
            if (cur.random != null) {
                copyNode.random = cur.random.next;
            } else {
                copyNode.random = null;
            }

            cur = next;
        }

        cur = head;
        copyNode = head.next;
        while (cur.next != null) {
            next = cur.next;

            cur.next = cur.next.next;

            cur = next;
        }
        return copyNode;
    }

    public static void printNodeWhitRand(Node head) {
        while (head != null) {
            System.out.print("|v:" + head.value + " r:" + (head.random == null ? "null" : head.random.value));
            head = head.next;
        }
        System.out.println();
    }

    public static Node generateNodeWithRand(int maxSize, int maxValue) {
        Node[] arr = new Node[(int) ((maxSize + 1) * Math.random())];
        int i = 0;
        for (i = 0; i < arr.length; i++) {
            arr[i] = new Node((int) ((maxValue + 1) * Math.random()));
        }
        for (i = 1; i < arr.length; i++) {
            arr[i - 1].next = arr[i];
            arr[i - 1].random = arr[(int) (arr.length * Math.random())];
        }

        return arr[0];
    }

    public static void main(String[] args) {
//        Node node1 = new Node(1);
//        Node node2 = new Node(2);
//        node1.next = node2;
//        node1.random = node2;
//        node2.next = null;
//        node2.random = node1;
//        Node res1 = copyListWithRand1(node1);
//        Node res2 = copyListWithRand2(node1);
//        printNodeWhitRand(node1);
//        printNodeWhitRand(res1);
//        printNodeWhitRand(res2);
//        System.out.println("==========");
        Node randNode = generateNodeWithRand(10, 10);
        Node res1 = copyListWithRand1(randNode);
        Node res2 = copyListWithRand2(randNode);
        printNodeWhitRand(randNode);
        printNodeWhitRand(res1);
        printNodeWhitRand(res2);
    }
}
