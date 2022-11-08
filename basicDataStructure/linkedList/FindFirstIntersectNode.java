package basicDataStructure.linkedList;

// 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
//【要求】
// 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。


import tool.Node;

public class FindFirstIntersectNode {

    public static Node findFirstIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = findFirstEnterNode(head1);
        Node loop2 = findFirstEnterNode(head2);
        if (loop1 == loop2) {
            return noLoop(head1, head2, loop1);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, head2);
        }
        return null;
    }


    // 判断一个单链表如果是否有环,如果有环,请返回第一个入环节点
    public static Node findFirstEnterNode(Node head) {
        if (head.next == null){
            return head;
        }
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    // 两个无环链表找到第一个相交节点
    public static Node noLoop(Node head1, Node head2, Node end) {
        Node cur1 = head1;
        Node cur2 = head2;
        int length1 = 1;
        int length2 = 1;
        while (cur1.next != end || cur2.next != end) {
            if (cur1.next != end) {
                cur1 = cur1.next;
                length1++;
            }
            if (cur2.next != end) {
                cur2 = cur2.next;
                length2++;
            }
        }
        // longList --> cur1
        // shortList --> cur1
        cur1 = length1 >= length2 ? head1 : head2;
        cur2 = head1 == cur1 ? head2 : head1;
        for (int i = 0; i < Math.abs(length1 - length2); i++) {
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    public static Node bothLoop(Node head1, Node head2) {
        Node cur1 = findFirstEnterNode(head1);
        Node cur2 = findFirstEnterNode(head2);
        if (cur1 == cur2) {
            return noLoop(head1, head2, cur1);
        }
        Node cur = cur1.next;
        while (cur != cur1) {
            if (cur == cur2) {
                return cur1;
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
//        Node head1 = new Node(1);
//        head1.next = new Node(2);
//        head1.next.next = new Node(3);
//        head1.next.next.next = new Node(4);
//        head1.next.next.next.next = new Node(5);
//        head1.next.next.next.next.next = new Node(6);
//        head1.next.next.next.next.next.next = new Node(7);
//        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4
//
//        // 0->9->8->2...
//        Node head2 = new Node(0);
//        head2.next = new Node(9);
//        head2.next.next = new Node(8);
//        head2.next.next.next = head1.next; // 8->2
////        System.out.println(findFirstIntersectNode(head1, head2).value);
//        Node end = findFirstEnterNode(head1);
//        System.out.println(noLoop(head1, head2, end).value);
    }
}
