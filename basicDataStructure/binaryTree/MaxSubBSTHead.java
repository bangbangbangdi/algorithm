package basicDataStructure.binaryTree;

// 给定一棵二叉树的头节点head，
// 返回这颗二叉树中最大的二叉搜索子树的头节点


import tool.BtNode;

public class MaxSubBSTHead {

    public static BtNode maxSubBSTHead(BtNode head) {
        if (head == null) {
            return null;
        }
        return process(head).maxHead;
    }

    public static Info process(BtNode head) {
        if (head == null) {
            return null;
        }
        Info left = process(head.left);
        Info right = process(head.right);

        int max = head.value;
        int min = head.value;
        int BSTSize = 1;
        boolean isBST = true;
        BtNode maxHead = head;

        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
            BSTSize = left.BSTSize;
            isBST = left.isBST && head.value > left.max;
            maxHead = left.maxHead;
        }

        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
            BSTSize = Math.max(BSTSize, right.BSTSize);
            isBST = isBST && right.isBST && head.value < right.min;
            maxHead = right.BSTSize == BSTSize ? right.maxHead : maxHead;
        }

        if (isBST) {
            maxHead = head;
            BSTSize++;
        }

        return new Info(max, min, BSTSize, isBST, maxHead);
    }

    public static class Info {
        public int max;
        public int min;
        public int BSTSize;
        public boolean isBST;
        public BtNode maxHead;

        public Info(int max, int min, int BSTSize, boolean isBST, BtNode maxHead) {
            this.max = max;
            this.min = min;
            this.BSTSize = BSTSize;
            this.isBST = isBST;
            this.maxHead = maxHead;
        }
    }

    public static void test() {
        BtNode n0 = new BtNode(0);
        BtNode n1 = new BtNode(3);
        BtNode n2 = new BtNode(17);
        BtNode n3 = new BtNode(2);
        BtNode n4 = new BtNode(4);
        BtNode n5 = new BtNode(6);
        BtNode n6 = new BtNode(8);
//        BtNode n7 = new BtNode(8);
//        BtNode n8 = new BtNode(9);
        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;
//        n3.left = n7;
//        n7.left = n8;
        System.out.println(maxSubBSTHead(n0).value);
    }

    public static void main(String[] args) {
        test();
    }
}