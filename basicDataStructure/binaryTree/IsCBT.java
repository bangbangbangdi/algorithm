package basicDataStructure.binaryTree;

// 给定一棵二叉树的头节点head，返回这颗二叉树中是不是完全二叉树

import tool.BtNode;

public class IsCBT {

    public static boolean isCBT(BtNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isCbt;
    }

    public static Info process(BtNode head) {
        if (head == null) {
            return new Info(true, true, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);

        int height = Math.max(left.height, right.height) + 1;
        boolean isFull = left.isFull && right.isCbt && (left.height == right.height);
        boolean isCbt = ((left.height == right.height) && (left.isFull && right.isCbt)) ||
                (((left.height > right.height) && (left.height - right.height) <= 1) && (left.isCbt && right.isFull));


        return new Info(isFull, isCbt,height);
    }

    public static class Info {
        public boolean isFull;
        public boolean isCbt;
        public int height;

        public Info(boolean isFull, boolean isCbt, int height) {
            this.isFull = isFull;
            this.isCbt = isCbt;
            this.height = height;
        }
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
//        n7.left = n8;
        System.out.println(isCBT(n0));
    }

    public static void main(String[] args) {
        test();
    }
}
