package my_practise.basic.myClass07;


import tool.BtNode;
import tool.Tools;

public class Code01_RecursiveTraversalBT {

    public static void pre(BtNode head){
        if(head == null){
            return ;
        }
        System.out.print(head.value + " ");
        pre(head.left);
        pre(head.right);
    }

    public static void in(BtNode head){
        if(head == null){
            return ;
        }
        in(head.left);
        System.out.print(head.value + " ");
        in(head.right);
    }

    public static void pos(BtNode head){
        if(head == null){
            return ;
        }
        pos(head.left);
        pos(head.right);
        System.out.print(head.value + " ");
    }

    public static void main(String[] args) {
        BtNode btNode1 = Tools.createSimpleBtNode();

        pre(btNode1);
        System.out.println();
        System.out.println("===========");
        in(btNode1);
        System.out.println();
        System.out.println("===========");
        pos(btNode1);
    }
}