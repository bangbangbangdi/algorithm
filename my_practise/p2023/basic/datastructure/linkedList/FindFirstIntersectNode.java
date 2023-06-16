package my_practise.p2023.basic.datastructure.linkedList;

import tool.Node;
import tool.Tools;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.linkedList
 * className:      FindFirstIntersectNode
 * author:     BangDi
 * description:
 * // 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
 * //【要求】
 * // 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
 * date:    2023/6/16 10:17
 * version:    1.0
 */
public class FindFirstIntersectNode {

    public static Node findFirstIntersectNode(Node head1,Node head2){
        if (head1 == null || head2 == null || head1 == head2){
            return head1;
        }
        Node f1 = findFirstEnterNode(head1);
        Node f2 = findFirstEnterNode(head2);
        // 无环或者同一个入环节点
        if (f1 == f2){
            return noLoopOrSameEntry(head1,head2,f1);
        }
        if (f1 == null || f2 == null){
            return null;
        }
        return bothLoop(f1, f2);
    }

    public static Node findFirstEnterNode(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node quick = head.next.next;
        Node slow = head.next;
        while (quick != null && quick.next != null && quick != slow) {
            quick = quick.next.next;
            slow = slow.next;
        }
        if (quick == null || quick.next == null) {
            return null;
        }
        quick = head;
        while (quick != slow) {
            quick = quick.next;
            slow = slow.next;
        }
        return slow;
    }

    // 没有环或者有相同的入环节点
    public static Node noLoopOrSameEntry(Node head1,Node head2,Node entryNode){
        if (head1 == null || head2 == null){
            return null;
        }
        int length1 = 0;
        int length2 = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        while(cur1 != entryNode){
            length1++;
            cur1 = cur1.next;
        }
        while(cur2 != entryNode){
            length2++;
            cur2 = cur2.next;
        }
        Node lHead = length1 > length2 ? head1 : head2;
        Node sHead = lHead == head1 ? head2 : head1;
        int step = Math.abs(length1 - length2);
        for (int i = 0; i < step; i++) {
            lHead = lHead.next;
        }
        while(lHead != entryNode){
            if (lHead == sHead){
                return lHead;
            }
            lHead = lHead.next;
            sHead = sHead.next;
        }
        return entryNode;
    }

    // 都有环但是入环节点不同,
    public static Node bothLoop(Node f1,Node f2){
        if (f1 == null || f2 == null){
            return null;
        }
        Node cur = f1.next;
        while(cur != f1){
            if (cur == f2){
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    public static void test() {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findFirstIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(findFirstIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findFirstIntersectNode(head1, head2).value);
    }

    public static void main(String[] args) {
        test();
    }
}
