package my_practise.p2023.basic.datastructure.binaryTree;

import tool.BtNode;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.binaryTree
 * className:      RecursiveTraversalBT
 * author:     BangDi
 * description:  递归遍历二叉树
 * date:    2023/6/5 16:53
 * version:    1.0
 */
public class RecursiveTraversalBT {

    public static void pre(BtNode node){
        if (node == null){
            return ;
        }
        System.out.print(node.value + "-");
        pre(node.left);
        pre(node.right);
    }

    public static void in(BtNode node){
        if (node == null){
            return ;
        }
        pre(node.left);
        System.out.print(node.value + "-");
        pre(node.right);
    }

    public static void post(BtNode node){
        if (node == null){
            return ;
        }
        pre(node.left);
        pre(node.right);
        System.out.print(node.value + "-");
    }


    public static void test() {
        BtNode n1 = new BtNode(1);
        BtNode n2 = new BtNode(2);
        BtNode n3 = new BtNode(3);
        BtNode n4 = new BtNode(4);
        BtNode n5 = new BtNode(5);
        BtNode n6 = new BtNode(6);
        BtNode n7 = new BtNode(7);
        BtNode n8 = new BtNode(8);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n4.left = n8;
        pre(n1);
//        in(n0);
//        post(n0);
    }

    public static void main(String[] args) {
        test();
    }

}
