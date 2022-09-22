package basicDataStructure.binaryTree;

// 给定一棵二叉树的头节点head，
// 返回这颗二叉树中最大的二叉搜索子树的大小

import tool.BtNode;

public class MaxSubBSTSize {

    public static int maxSubBSTSize(BtNode head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxSize;
    }

    public static Info process(BtNode head) {
        if (head == null) {
            return null;
        }
        Info left = process(head.left);
        Info right = process(head.right);

        int max = head.value;
        int min = head.value;
        int maxSize = 1;
        boolean isBST = true;

        if (left != null) {
            max = Math.max(left.max, max);
            min = Math.min(left.min, min);
            isBST = left.isBST && head.value > left.max;
            maxSize = left.maxSize;
        }

        if (right != null) {
            max = Math.max(right.max, max);
            min = Math.min(right.min, min);
            isBST = isBST && right.isBST && head.value < right.min;
            maxSize = Math.max(maxSize, right.maxSize);
        }

        if (isBST) {
            ++maxSize;
        }

        return new Info(max, min, maxSize, isBST);
    }

    public static class Info {
        public int max;
        public int min;
        public int maxSize;
        public boolean isBST;

        public Info(int max, int min, int maxSize, boolean isBST) {
            this.max = max;
            this.min = min;
            this.maxSize = maxSize;
            this.isBST = isBST;
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
        n7.left = n8;
        System.out.println(maxSubBSTSize(n0));
    }

    public static void main(String[] args) {
        test();
    }

}
