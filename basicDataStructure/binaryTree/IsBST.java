package basicDataStructure.binaryTree;

import tool.BtNode;

public class IsBST {

    public static boolean isBST(BtNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    public static Info process(BtNode head) {
        if (head == null) {
            return null;
        }
        Info left = process(head.left);
        Info right = process(head.right);

        boolean isBST = true;
        int max = head.value;
        int min = head.value;
        if (left != null) {
            isBST = left.isBST && (head.value > left.max);
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
        }
        if (right != null) {
            isBST = right.isBST && isBST && (head.value < right.min);
            max = Math.min(min, right.max);
            min = Math.min(min, right.min);
        }

        return new Info(isBST, max, min);
    }

    public static class Info {
        boolean isBST;
        int max;
        int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
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
