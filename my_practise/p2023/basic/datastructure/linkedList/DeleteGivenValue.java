package my_practise.p2023.basic.datastructure.linkedList;

import tool.Node;
import tool.Tools;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.linkedList
 * className:      DeleteGivenValue
 * author:     BangDi
 * description:
 * 删除指定数字的节点
 * 有多少删多少
 * 这道题会涉及到换头节点的问题
 * <p>
 * date:    2023/6/16 9:32
 * version:    1.0
 */
public class DeleteGivenValue {

    public static Node deleteGivenValue(Node head, int value) {
        if (head == null) {
            return null;
        }
        Node res = null;
        Node cur = head;
        Node next = null;
        Node pre = null;
        while (cur != null) {
            next = cur.next;
            if (cur.value == value) {
                if (pre != null){
                    pre.next = next;
                }
                cur.next = null;
            } else {
                res = res == null ? cur : res;
                pre = cur;
            }
            cur = next;
        }
        return res;
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
