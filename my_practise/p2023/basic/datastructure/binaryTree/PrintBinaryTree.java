package my_practise.p2023.basic.datastructure.binaryTree;

import tool.BtNode;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.binaryTree
 * className:      PrintBinaryTree
 * author:     BangDi
 * description:  打印二叉树
 * date:    2023/6/5 16:21
 * version:    1.0
 */
public class PrintBinaryTree {

    public static void printBinaryTree(BtNode head){
        if (head == null){
            return ;
        }
        process(head,0,"H");
    }

    public static void process(BtNode node,int level,String flag){
        if (node == null){
            return;
        }
        process(node.left,level+1,"v");
        printNode(node.value,level,flag);
        process(node.right,level+1,"^");
    }

    public static void printNode(int value,int level,String flag){
        final int LENGTH = 12;
        int preSpace = level * LENGTH + (LENGTH - String.valueOf(value).length() - 2)/2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < preSpace; i++) {
            sb.append(" ");
        }
        sb.append(flag);
        sb.append(value);
        sb.append(flag);
        System.out.println(sb);
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
