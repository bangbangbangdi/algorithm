package my_practise.p2023.basic.datastructure.linkedList;

import tool.Node;
import tool.Tools;

public class ReverseList {

    public static Node reverseList(Node head) {
        if (head == null) {
            return null;
        }
        Node next = null;
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node[] nodes = Tools.generateRandomNodeArray(10, 10);
        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        Tools.printAllNodes(nodes[0]);
        Tools.printAllNodes(reverseList(nodes[0]));
    }
}
