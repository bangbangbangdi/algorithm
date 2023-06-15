package my_practise.p2023.basic.datastructure.linkedList;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure.linkedList
 * className:      CopyListWithRandom
 * author:     BangDi
 * description:  TODO
 * //一种特殊的单链表节点类描述如下
 * //class Node {
 * //    int value;
 * //    Node next;
 * //    Node rand;
 * //    Node(int val) { value = val; }
 * //}
 * //rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
 * //        给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * //        【要求】
 * //        时间复杂度O(N)，额外空间复杂度O(1)
 * date:    2023/6/14 23:55
 * version:    1.0
 */
public class CopyListWithRandom {

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node copyListWithRandom(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            Node copy = new Node(cur.value);
            cur.next = copy;
            copy.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = head.next;
        while (cur != null) {
            next = cur.next.next;
            curCopy.rand = cur.rand == null ? null : cur.rand.next;
            cur = next;
            curCopy = cur == null ? null : cur.next;
        }
        Node copyHead = head.next;
        cur = head;
        while(cur != null){
            next = cur.next;
            cur.next = cur.next == null ? null : cur.next.next;
            cur = next;
        }
        return copyHead;
    }

}
