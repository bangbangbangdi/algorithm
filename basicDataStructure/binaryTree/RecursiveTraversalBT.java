package basicDataStructure.binaryTree;

//先序：任何子树的处理顺序都是，先头节点、再左子树、然后右子树
//中序：任何子树的处理顺序都是，先左子树、再头节点、然后右子树
//后序：任何子树的处理顺序都是，先左子树、再右子树、然后头节点


import tool.BtNode;
import tool.Tools;

public class RecursiveTraversalBT {

    public static void pre(BtNode head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    public static void in(BtNode head){
        if (head == null){
            return ;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    public static void post(BtNode head){
        if (head == null){
            return;
        }
        post(head.left);
        post(head.right);
        System.out.println(head.value);
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
//        pre(n0);
//        in(n0);
        post(n0);
    }

    public static void main(String[] args) {
        test();
    }

}
