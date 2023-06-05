package my_practise.p2023.basic.datastructure.binaryTree;

import tool.BtNode;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure
 * className:      IsFull
 * author:     BangDi
 * description:  A full binary tree is a binary tree in which all of the nodes have either 0 or 2 offspring.
 * In other terms, a full binary tree is a binary tree in which all nodes, except the leaf nodes, have two offspring.
 * 以上是国外对满二叉树的定义
 * 国内的定义是如果一个二叉树的高度为K,节点为2^k-1则该数为满二叉树
 * date:    2023/6/3 11:40
 * version:    1.0
 */
public class IsFull {

    public static boolean isFull(BtNode node) {
        if (node == null) {
            return true;
        }
        Info info = process(node);
        return (Math.pow(2,info.height) - 1) == info.cnt;
    }

    public static Info process(BtNode node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info left = process(node.left);
        Info right = process(node.right);
        return new Info(Math.max(left.height, right.height) + 1, left.cnt + right.cnt + 1);
    }

    public static class Info {
        int height;
        int cnt;

        public Info(int height, int cnt) {
            this.height = height;
            this.cnt = cnt;
        }
    }


    public static void test() {
        BtNode n0 = new BtNode(1);
        BtNode n1 = new BtNode(2);
        BtNode n2 = new BtNode(3);
        BtNode n3 = new BtNode(4);
        BtNode n4 = new BtNode(5);
        BtNode n5 = new BtNode(6);
        BtNode n6 = new BtNode(7);
        BtNode n7 = new BtNode(8);
        BtNode n8 = new BtNode(9);
        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;
        n3.left = n7;
        n3.right = n8;
        System.out.println(isFull(n0));
//        System.out.println(isFullOther(n0));
    }

    public static void main(String[] args) {
        test();
    }

}
