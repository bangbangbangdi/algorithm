package my_practise.basic.myClass07;


import tool.BtNode;
import tool.Tools;

public class Code05_PrintBinaryTree {
    public static void printTree(BtNode head) {
        printInOrder(head,0,10,"Head");
    }

    public static void printInOrder(BtNode head, int level, int length, String to) {
        if (head == null) {
            return;
        }
        printInOrder(head.left, level + 1, length, "v");
        String str = String.valueOf(head.value);
        str = to + str + to;
        int leftSpace = (length - str.length()) >> 1;
        leftSpace = leftSpace + length * level;
        str = getSpace(leftSpace) + str;
        System.out.println(str);
        printInOrder(head.right, level + 1, length, "^");
    }

    // 获取空格字符串
    public static String getSpace(int num) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < num; i++) {
            buf.append(" ");
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        BtNode node = Tools.createSimpleBtNode();
        printTree(node);
    }
}
