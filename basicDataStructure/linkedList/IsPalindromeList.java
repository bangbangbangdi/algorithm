package basicDataStructure.linkedList;

//给定一个单链表的头节点head，请判断该链表是否为回文结构。
// 1）哈希表方法特别简单（笔试用）
// 2）改原链表的方法就需要注意边界了（面试用）


import tool.Node;
import tool.Tools;

import java.util.ArrayList;

public class IsPalindromeList {

    public static boolean hashWay(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        int midIndex = arr.size() / 2;
        for (int i = 0; i <= midIndex; i++) {
            if (arr.get(i).value != arr.get(arr.size() - i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static boolean normalWay(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 找到中点 Or 上中点
        Node n1 = head;
        Node n2 = head;
        Node n3 = null;
        boolean res = true;
        while (n1.next != null && n1.next.next != null) {
            n1 = n1.next.next;
            n2 = n2.next;
        }
        n1 = n2.next;
        n2.next = null;
        n3 = n2;
        // 反转右半部分
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        n1 = head;
        n2 = n3;
        // 确认结果
        while (n1 != null && n3 != null) {
            if (n1.value != n3.value) {
                res = false;
                break;
            }
            n1 = n1.next;
            n3 = n3.next;
        }
        // 把右半部分再拧回来
        n1 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        return res;
    }

    public static void test() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(2);
        Node n5 = new Node(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        System.out.println(normalWay(n1));
        Tools.printAllNodes(n1);
    }

    public static void main(String[] args) {
        test();
    }

}
