package basicDataStructure.binaryTree;

import tool.BtNode;

public class lowestAncestor {

    public static BtNode lowestAncestor(BtNode head, BtNode a, BtNode b) {
        if (head == null || a == null || b == null) {
            return null;
        }
        return process2(head, a, b).ancestor;
    }

    public static Info process(BtNode head, BtNode a, BtNode b) {
        if (head == null) {
            return new Info(null, null);
        }
        Info left = process(head.left, a, b);
        Info right = process(head.right, a, b);
        if (left.AAncestor != null && left.BAncestor != null) {
            return left;
        }
        if (right.AAncestor != null && right.BAncestor != null) {
            return right;
        }
//        BtNode AAncestor = left.AAncestor == null ? ;
        BtNode BAncestor = null;
        return null;
    }

    public static class Info {
        public BtNode AAncestor;
        public BtNode BAncestor;

        public Info(BtNode AAncestor, BtNode BAncestor) {
            this.AAncestor = AAncestor;
            this.BAncestor = BAncestor;
        }
    }

    public static Info2 process2(BtNode head, BtNode a, BtNode b) {
        if (head == null) {
            return new Info2(null, false);
        }

        Info2 left = process2(head.left, a, b);
        Info2 right = process2(head.right, a, b);

        if (left.finish) {
            return left;
        }
        if (right.finish) {
            return right;
        }

        boolean finish = (head == a || head == b) && (left.ancestor != null || right.ancestor != null);
        BtNode ancestor = (left.ancestor != null || right.ancestor != null || head == a || head == b) ? head : null;

        return new Info2(ancestor, finish);
    }

    public static class Info2 {
        public BtNode ancestor;
        public boolean finish;

        public Info2(BtNode ancestor, boolean finish) {
            this.ancestor = ancestor;
            this.finish = finish;
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
        System.out.println(lowestAncestor(n0, n1, n3).value);
    }

    public static void main(String[] args) {
        test();
    }


}
