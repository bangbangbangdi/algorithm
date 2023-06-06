package my_practise.p2023.basic.datastructure.binaryTree;

import tool.BtNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.binaryTree
 * className:      SerializeAndReconstructTree
 * author:     BangDi
 * description:  序列化与反序列化
 * date:    2023/6/6 9:26
 * version:    1.0
 */
public class SerializeAndReconstructTree {

    public static Queue<Integer> preSerialize(BtNode node) {
        if (node == null) {
            return null;
        }
        Queue<Integer> queue = new LinkedList<>();
        pre(node, queue);
        return queue;
    }

    public static void pre(BtNode cur, Queue<Integer> queue) {
        if (cur == null) {
            queue.add(null);
            return;
        }
        queue.add(cur.value);
        pre(cur.left, queue);
        pre(cur.right, queue);
    }

    public static BtNode preReconstruct(Queue<Integer> queue) {
        if (queue == null) {
            return null;
        }
    }

    public static void preRec(BtNode head, Queue<Integer> queue) {
        if (queue == null || queue.isEmpty()) {
            return;
        }
        if (queue.peek() == null){
            queue.poll();
            return;
        }
        Integer poll = queue.poll();
        BtNode cur = new BtNode(poll);
        pre();
    }

    public static void test() {
        BtNode n1 = new BtNode(1);
        BtNode n2 = new BtNode(2);
        BtNode n3 = new BtNode(3);
        BtNode n4 = new BtNode(4);
        BtNode n5 = new BtNode(5);
        BtNode n6 = new BtNode(6);
        BtNode n7 = new BtNode(7);
        BtNode n8 = new BtNode(8);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n4.left = n8;
        Queue<Integer> queue = preSerialize(n1);
        for (Integer integer : queue) {
            System.out.print(integer + "-");
        }
//        in(n0);
//        post(n0);
    }

    public static void main(String[] args) {
        test();
    }

}
