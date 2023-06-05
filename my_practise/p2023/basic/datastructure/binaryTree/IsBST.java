package my_practise.p2023.basic.datastructure.binaryTree;

import tool.BtNode;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure
 * className:      IsBST
 * author:     BangDi
 * description:
 * Binary Search Tree is a node-based binary tree data structure which has the following properties:
 * The left subtree of a node contains only nodes with keys lesser than the node’s key.
 * The right subtree of a node contains only nodes with keys greater than the node’s key.
 * The left and right subtree each must also be a binary search tree.
 * date:    2023/6/2 23:49
 * version:    1.0
 */
public class IsBST {

    public static boolean isBST(BtNode node) {
        if (node == null) {
            return false;
        }
        return process(node).isBST;
    }

    public static class Info {
        int max;
        int min;
        boolean isBST;

        public Info(int max, int min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }

    public static Info process(BtNode node) {
        if (node == null) {
            return new Info(Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        }
        Info left = process(node.left);
        Info right = process(node.right);
        int max = Math.max(Math.max(left.max, right.max), node.value);
        int min = Math.min(Math.min(left.min, right.min), node.value);
        boolean isBST = node.left == null || (left.isBST && left.max < node.value);
        isBST = isBST && (node.right == null || (right.isBST && right.min > node.value));
        return new Info(max,min,isBST);
    }

    public static void test() {
        BtNode n0 = new BtNode(5);
        BtNode n1 = new BtNode(3);
        BtNode n2 = new BtNode(7);
        BtNode n3 = new BtNode(2);
        BtNode n4 = new BtNode(4);
        BtNode n5 = new BtNode(6);
        BtNode n6 = new BtNode(7);
        BtNode n7 = new BtNode(8);
        BtNode n8 = new BtNode(9);
        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
//        n2.right = n6;
//        n3.left = n7;
//        n7.left = n8;
        System.out.println(isBST(n0));
    }

    public static void main(String[] args) {
        test();
    }

}