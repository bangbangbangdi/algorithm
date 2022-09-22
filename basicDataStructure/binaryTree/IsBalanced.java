package basicDataStructure.binaryTree;

// 给定一颗二叉树的头节点head,返回这棵树是不是平衡二叉树

import tool.BtNode;

public class IsBalanced {

    public static boolean isBalanced(BtNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isBalance;
    }

    public static Info process(BtNode head) {
        if (head == null) {
            return new Info(true, 0);
        }
        Info lInfo = process(head.left);
        Info rInfo = process(head.right);
        int height = Math.max(lInfo.height, rInfo.height) + 1;
        boolean isBal = (Math.abs(lInfo.height - rInfo.height) <= 1) && lInfo.isBalance && rInfo.isBalance;
        return new Info(isBal, height);
    }

    public static class Info {
        public boolean isBalance;
        public int height;

        public Info(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public static boolean isBalanced2(BtNode head) {
        if (head == null) {
            return true;
        }
        boolean[] ans = {true};
        process(head,ans);
        return ans[0];
    }

    public static int process(BtNode head, boolean[] isFull) {
        if (head == null || !isFull[0]) {
            return -1;
        }
        int left = process(head.left, isFull);
        int right = process(head.right, isFull);
        isFull[0] = Math.abs(left - right) <= 1;
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
        n7.left = n8;
        System.out.println(isBalanced(n0));
        System.out.println(isBalanced2(n0));
    }

    public static void test(String[] b) {
        b[0] = "aa";
    }

    public static void main(String[] args) {
        test();
    }
}
