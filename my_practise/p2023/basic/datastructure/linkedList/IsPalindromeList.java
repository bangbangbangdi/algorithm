package my_practise.p2023.basic.datastructure.linkedList;

import tool.Node;
import tool.Tools;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure.linkedList
 * className:      IsPalindromeList
 * author:     BangDi
 * description:  TODO
 //给定一个单链表的头节点head，请判断该链表是否为回文结构。
 // 1）哈希表方法特别简单（笔试用）
 // 2）改原链表的方法就需要注意边界了（面试用）
 * date:    2023/6/16 20:36
 * version:    1.0
 */
public class IsPalindromeList {

    public static boolean isPalindromeList(Node head){
        if (head == null){
            return true;
        }
        Node mid = head;
        Node cur = head;
        Node pre = null;
        Node next = null;
        boolean ans = true;
        // 取得上中点
        while(cur != null && cur.next != null){
            mid = mid.next;
            cur = cur.next.next;
        }
        // 反转后半部分
        cur = mid.next;
        pre = mid;
        mid.next = null;
        while(cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 检查是否为回文
        cur = head;
        Node cur2 = pre;
        while(cur != null && cur2 != null){
            if (cur.value != cur2.value && ans){
                ans = false;
                break;
            }
            cur = cur.next;
            cur2 = cur2.next;
        }
        // 再拧回去
        cur = pre;
        pre = null;
        while(cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return ans;
    }

    public static void test() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(2);
        Node n5 = new Node(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        System.out.println(isPalindromeList(n1));
        Tools.printAllNodes(n1);
    }

    public static void main(String[] args) {
        test();
    }

}