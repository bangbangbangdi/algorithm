package basicDataStructure.binaryTree;

// 国内-满二叉树:三角形
// 国外-满二叉树:要么是叶子节点要么是度为2的节点(有左子树和又子树)

import tool.BtNode;

public class IsFull {

    public static boolean isFullMyCountry(BtNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isFull;
    }

    public static Info process(BtNode head) {
        if (head == null) {
            return new Info(true, 0, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int count = left.count + right.count + 1;
        int level = Math.max(left.level, right.level) + 1;
        boolean isFull = (Math.pow(2, level) - 1) == count;
        return new Info(isFull, count, level);
    }

    public static class Info {
        public boolean isFull;
        public int count;
        public int level;

        public Info(boolean isFull, int count, int level) {
            this.isFull = isFull;
            this.count = count;
            this.level = level;
        }
    }

    public static boolean isFullOther(BtNode head) {
        if (head == null) {
            return true;
        }
        return process2(head).isFull;
    }

    public static Info2 process2(BtNode head) {
        if (head == null) {
            return new Info2(true, true);
        }
        Info2 left = process2(head.left);
        Info2 right = process2(head.right);
        boolean isNull = false;
        boolean isFull = (left.isFull && right.isFull) && (left.isNull == right.isNull);
        return new Info2(isFull, isNull);
    }

    public static class Info2 {
        public boolean isFull;
        public boolean isNull;

        public Info2(boolean isFull, boolean isNull) {
            this.isFull = isFull;
            this.isNull = isNull;
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
        System.out.println(isFullMyCountry(n0));
        System.out.println(isFullOther(n0));
    }

    public static void main(String[] args) {
        test();
    }

}
