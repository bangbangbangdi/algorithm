package my_practise.basic.myClass06;

import tool.Node;
import tool.Tools;

import java.util.HashSet;

public class Code05_FindFirstIntersectNode {

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node s = head.next;
        Node f = head.next.next;
        while (s != f) {
            if (f.next == null || f.next.next == null) {
                return null;
            }
            s = s.next;
            f = f.next.next;
        }
        f = head;
        while (s != f) {
            s = s.next;
            f = f.next;
        }
        return s;
    }

    public static Node getLoopNode2(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node cur = head;
        HashSet<Node> set = new HashSet<>();
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }
        return null;
    }

    public static Node noLoop(Node head1, Node head2) {
        Node cur1 = head1;
        Node cur2 = head2;
        int size1 = 0;
        int size2 = 0;

        while (cur1 != null) {
            size1++;
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            size2++;
            cur2 = cur2.next;
        }
//        System.out.println("size1:" + size1 + " size2:" + size2);
        // 区分出长list和短list
        cur1 = size1 - size2 >= 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;

        // 得出差值
        size1 = Math.abs(size1 - size2);
//        System.out.println("dis"+size1);
        while (size1 > 0) {
            size1--;
            cur1 = cur1.next;
        }

        while (cur1 != null) {
            if (cur1 == cur2) {
                return cur1;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return null;
    }

    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = head1;
        Node cur2 = head2;
        // 入环节点相同的情况,则必有相交点，
        if (loop1 == loop2) {
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n > 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else { // 入环节点不相同的情况
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return cur1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Node head = Tools.createNodes(arr);
        Node head2 = Tools.createNodes(arr2);
        Node cur = head;
        Node cur2 = head2;
        while (cur.next != null) {
            cur = cur.next;
        }

        while (cur2.next != null) {
            cur2 = cur2.next;
        }
        cur.next = head.next.next;
        cur2.next = head.next;

        Node iNode = getIntersectNode(head, head2);
        System.out.println(iNode == null ? "null" : iNode.value);

//        Node loop = getLoopNode(head);
//        Node loop2 = getLoopNode2(head2);
//        Node node = bothLoop(head, loop, head2, loop2);
//        System.out.println(node == null ? "null" : node.value);

//        System.out.println(getLoopNode2(head2) == null ? "null" : getLoopNode2(head2).value);


//        System.out.println(noLoop(head, head2) == null ? "null" : noLoop(head, head2).value);
//        cur.next = head.next.next.next.next.next;
    }
}
