package my_practise.p2023.basic.datastructure.binaryTree;

import tool.BtNode;
import tool.Node;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure
 * className:      IsBalanced
 * author:     BangDi
 * description:  平衡二叉树
 * // 给定一颗二叉树的头节点head,返回这棵树是不是平衡二叉树
 * balancedTree定义:一颗空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一颗平衡二叉树
 * date:    2023/6/2 22:59
 * version:    1.0
 */
public class IsBalanced {

    public static boolean isBalanced(BtNode node) {
        if (node == null) {
            return false;
        }
        return process(node).isBalanced;
    }

    static class Info {
        boolean isBalanced;
        int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static Info process(BtNode node) {
        if (node == null) {
            return new Info(true, 0);
        }
        Info left = process(node.left);
        Info right = process(node.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <= 1;
        return new Info(isBalanced, height);
    }

    public static boolean isBalanced2(BtNode node) {
        if (node == null) {
            return false;
        }
        boolean[] isBalanced = {false};
        process2(node,isBalanced);
        return isBalanced[0];
    }

    public static int process2(BtNode node, boolean[] isBalanced) {
        if (node == null) {
            return 0;
        }
        int left = process2(node.left, isBalanced);
        int right = process2(node.right, isBalanced);
        isBalanced[0] = Math.abs(left - right) <= 1;
        return Math.max(left, right) + 1;
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
        n7.right = n8;
        System.out.println(isBalanced(n0));
        System.out.println(isBalanced2(n0));
    }

    public static void main(String[] args) {
        test();
    }
}