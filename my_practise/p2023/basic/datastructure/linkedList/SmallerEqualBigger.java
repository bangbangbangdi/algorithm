package my_practise.p2023.basic.datastructure.linkedList;

import tool.Node;
import tool.Tools;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.linkedList
 * className:      SmallerEqualBigger
 * author:     BangDi
 * description:
 * // 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * // 1）把链表放入数组里，在数组上做partition（笔试用）
 * // 2）分成小、中、大三部分，再把各个部分之间串起来（面试用）
 * date:    2023/6/19 11:45
 * version:    1.0
 */
public class SmallerEqualBigger {

    public static Node normalWay(Node head, int value) {
        if (head == null) {
            return null;
        }
        Node sHead = null;
        Node sTail = null;
        Node eHead = null;
        Node eTail = null;
        Node bHead = null;
        Node bTail = null;
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = null;
            if (cur.value < value) {
                if (sHead == null) {
                    sHead = cur;
                } else {
                    sTail.next = cur;
                }
                sTail = cur;
            } else if (cur.value > value) {
                if (bHead == null) {
                    bHead = cur;
                }else {
                    bTail.next = cur;
                }
                bTail = cur;
            }else {
                if (eHead == null){
                    eHead = cur;
                }else {
                    eTail.next = cur;
                }
                eTail = cur;
            }
            cur = next;
        }
        if (sTail != null) {
            sTail.next = eHead != null ? eHead : bHead;
        }
        if (eTail != null) {
            eTail.next = bHead;
        }
        return sHead != null ? sHead : eHead != null ? eHead : bHead;
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
//        nodePartition(n1, 2);
        normalWay(n1, 2);
        Tools.printAllNodes(n1);
    }

    public static void main(String[] args) {
        test();
    }

}
