package my_practise.p2023.basic.datastructure.linkedList;

import tool.Node;
import tool.Tools;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure.linkedList
 * className:      LinkedListMid
 * author:     BangDi
 * description:  TODO
 * // 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * // 2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 * // 3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 * // 4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 * date:    2023/6/16 21:16
 * version:    1.0
 */
public class LinkedListMid {

    public static Node upMid(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head.next;
        Node upMid = head;
        while (cur != null && cur.next != null) {
            upMid = upMid.next;
            cur = cur.next.next;
        }
        return upMid;
    }

    public static Node downMid(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node downMid = head;
        while (cur != null && cur.next != null) {
            downMid = downMid.next;
            cur = cur.next.next;
        }
        return downMid;
    }

    public static Node preUpMid(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node cur = head.next.next.next;
        Node preUpMid = head;
        while (cur != null && cur.next != null) {
            preUpMid = preUpMid.next;
            cur = cur.next.next;
        }
        return preUpMid;
    }

    public static Node preDownMid(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node cur = head.next.next;
        Node preDownMid = head;
        while (cur != null && cur.next != null) {
            preDownMid = preDownMid.next;
            cur = cur.next.next;
        }
        return preDownMid;
    }

    public static void test() {
        Node[] arr = Tools.generateRandomNodeArray(10, 10);
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i].next = arr[i + 1];
        }
        Tools.printAllNodes(arr);
        System.out.println(arr.length);
        System.out.println("up " + upMid(arr[0]).value);
        System.out.println("down " + downMid(arr[0]).value);
        System.out.println("preUp " + preUpMid(arr[0]).value);
        System.out.println("preDown " + preDownMid(arr[0]).value);
    }

    public static void main(String[] args) {
        test();
    }

}