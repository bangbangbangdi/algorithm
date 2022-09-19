package basicDataStructure.binaryTree;

import tool.BtNode;

public class PrintBinaryTree {

    public static void printBinaryTree(BtNode head) {
        if (head == null) {
            return;
        }
        process(head, 0);
    }

    public static void process(BtNode head, int level) {
        if (head == null) {
            return;
        }
        process(head.left, level + 1);
        System.out.println(getStr(head.value, level));
        process(head.right, level + 1);
    }

    public static String getStr(int value, int level) {
        int len = String.valueOf(value).length();
        int left = (12 - len) / 2;
        int right = 12 - len - left;
        return getSpace(level * 12) + getSpace(left) + value + getSpace(right);
    }

    public static String getSpace(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(" ");
        }
        return sb.toString();
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
        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;
        n3.left = n7;
        printBinaryTree(n0);
    }

    public static void main(String[] args) {
        test();
    }

}
