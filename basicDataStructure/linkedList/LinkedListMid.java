package basicDataStructure.linkedList;

// 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
// 2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
// 3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
// 4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个


import tool.Node;
import tool.Tools;

public class LinkedListMid {

    public static Node midOrUpMidNode(Node head) {
        if (head == null) {
            return null;
        }
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static Node midOrDownNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node fast = head.next;
        Node slow = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void test() {
        Node[] arr = Tools.generateRandomNodeArray(10, 10);
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i].next = arr[i + 1];
        }
        Tools.printAllNodes(arr);
        System.out.println(midOrDownNode(arr[0]).value);
    }

    public static void main(String[] args) {
        test();
    }

}
