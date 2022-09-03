package basicDataStructure.linkedList;

import tool.Node;
import tool.Tools;

// 删除指定数字的节点
// 有多少删多少
public class DeleteGivenValue {

    public static Node deleteGivenValue(Node head, int value) {
        if (head == null) {
            return null;
        }
        while (head != null) {
            if (head.value != value) {
                break;
            }
            head = head.next;
        }
        Node cur = head;
        Node pre = head;
        while (cur != null) {
            if (cur.value == value) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
//        Node[] nodes = Tools.generateRandomNodeArray(20, 10);
        Node[] nodes = {new Node(4), new Node(4), new Node(1), new Node(0), new Node(4)};
        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        Tools.printAllNodes(nodes[0]);
        Tools.printAllNodes(deleteGivenValue(nodes[0], 4));
    }

}
