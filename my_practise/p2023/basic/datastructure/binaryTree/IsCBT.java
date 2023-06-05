package my_practise.p2023.basic.datastructure.binaryTree;

import tool.BtNode;
import tool.Tools;

import java.util.LinkedList;
import java.util.Queue;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure
 * className:      IsCBT
 * author:     BangDi
 * description:
 * A complete binary tree is a special type of binary tree where all the levels of the
 * tree are filled completely except the lowest level nodes which are filled from as left as possible.
 * date:    2023/6/3 09:46
 * version:    1.0
 */
public class IsCBT {

    public static boolean isCBT(BtNode node) {
        if (node == null) {
            return true;
        }
        return process(node).isCbt;
    }

    public static Info process(BtNode node) {
        if (node == null) {
            return new Info(true, true, 0);
        }
        Info left = process(node.left);
        Info right = process(node.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isFull = left.isFull && right.isFull && left.height == right.height;
        boolean isCbt = ((left.height == right.height) && left.isFull && right.isCbt) ||
                ((left.height - right.height == 1) && right.isFull && left.isCbt);
        return new Info(isFull, isCbt, height);
    }

    public static class Info {
        boolean isFull;
        boolean isCbt;
        int height;

        public Info(boolean isFull, boolean isCbt, int height) {
            this.isFull = isFull;
            this.isCbt = isCbt;
            this.height = height;
        }
    }

    public static boolean compare(BtNode node) {
        if (node == null) {
            return true;
        }
        Queue<BtNode> queue = new LinkedList<>();
        Queue<BtNode> queue2 = new LinkedList<>();
        queue.add(node);
        queue2.add(node);
        while (!queue.isEmpty()) {
            BtNode cur = queue.poll();
            queue2.add(cur.left);
            queue2.add(cur.right);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        BtNode cur = queue2.poll();
        BtNode pre = null;
        while (!queue2.isEmpty()) {
            pre = cur;
            cur = queue2.poll();
            if (pre == null && cur != null) {
                return false;
            }
        }
        return true;
    }

    public static boolean isCBT1(BtNode head) {
        if (head == null) {
            return true;
        }
        LinkedList<BtNode> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        BtNode l = null;
        BtNode r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                    (leaf && !(l == null && r == null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    public static void test() {
        BtNode n0 = new BtNode(5);
        BtNode n1 = new BtNode(3);
        BtNode n2 = new BtNode(7);
        BtNode n3 = new BtNode(2);
        BtNode n4 = new BtNode(4);
        BtNode n5 = new BtNode(6);
        BtNode n6 = new BtNode(8);
        BtNode n7 = new BtNode(8);
        BtNode n8 = new BtNode(9);
        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;
        n3.left = n7;
        n7.left = n8;
        System.out.println(isCBT(n0));
        System.out.println(compare(n0));
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        boolean succeed = true;
        for (int i = 0; i < testTimes && succeed; i++) {
            BtNode head = Tools.generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != compare(head)) {
                Tools.printBinaryTree(head);
                System.out.println("Oops!");
                succeed = false;
            }
        }
        System.out.println("finish!");
    }
}
