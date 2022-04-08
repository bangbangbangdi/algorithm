package myClass08;

import tool.BtNode;

public class Code07_lowestAncestor {

    public static BtNode lowestAncestor1(BtNode head, BtNode a, BtNode b) {
        if (head == null || a == null || b == null) {
            return null;
        }
        return process1(head, a, b).lowestAncestor;
    }

    public static class Info {
        public boolean isAAncestor;
        public boolean isBAncestor;
        public BtNode lowestAncestor;

        public Info(boolean isAAncestor, boolean isBAncestor, BtNode lowestAncestor) {
            this.isAAncestor = isAAncestor;
            this.isBAncestor = isBAncestor;
            this.lowestAncestor = lowestAncestor;
        }
    }

    public static Info process1(BtNode x, BtNode a, BtNode b) {
        if (x == null) {
            return new Info(false, false, null);
        }

        Info leftInfo = process1(x.left, a, b);
        Info rightInfo = process1(x.right, a, b);


        if (leftInfo.lowestAncestor != null) {
            return leftInfo;
        }
        if (rightInfo.lowestAncestor != null) {
            return rightInfo;
        }

        boolean isAAncestor = x == a || leftInfo.isAAncestor || rightInfo.isAAncestor;
        boolean isBAncestor = x == b || leftInfo.isBAncestor || rightInfo.isBAncestor;

        BtNode lowestAncestor = isAAncestor && isBAncestor ? x : null;

        return new Info(isAAncestor, isBAncestor, lowestAncestor);
    }

    public static void main(String[] args) {
        BtNode node1 = new BtNode(1);
        BtNode node2 = new BtNode(2);
        BtNode node3 = new BtNode(3);
        BtNode node4 = new BtNode(4);
        BtNode node5 = new BtNode(5);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        System.out.println(lowestAncestor1(node1, node4, node5).value);
    }
}
