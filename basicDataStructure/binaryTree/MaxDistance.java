package basicDataStructure.binaryTree;

// 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，
// 返回整棵二叉树的最大距离


import tool.BtNode;

public class MaxDistance {

    public static int maxDistance(BtNode head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxDistance;
    }

    public static Info process(BtNode head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);

        int height = Math.max(left.height, right.height) + 1;
        int maxDistance = Math.max(left.height+right.height,Math.max(left.maxDistance,right.maxDistance));
        return new Info(height, maxDistance);
    }

    public static class Info {
        public int height;
        public int maxDistance;

        public Info(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
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
        n1.left = n2;
        n2.left = n3;
        n2.right = n4;
        n3.left = n5;
        n4.right = n6;
        n6.right = n7;
        System.out.println(maxDistance(n0));
    }

    public static void main(String[] args) {
        test();
    }
}
