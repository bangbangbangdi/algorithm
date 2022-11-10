package my_practise.basic.myClass07;


import tool.BtNode;
import tool.Tools;

import java.util.Stack;

public class Code02_UnRecursiveTraversalBT {

    public static void pre(BtNode head) {
        if (head == null) {
            return;
        }
        System.out.println("pre-order:");
        Stack<BtNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.print(head.value + " ");
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }
    }

    public static void in(BtNode head) {
        System.out.println("in-order");
        Stack<BtNode> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if(head != null){
                stack.push(head);
                head = head.left;
            }else{
                head = stack.pop();
                System.out.print(head.value + " ");
                head = head.right;
            }
        }
    }

    public static void pos(BtNode head) {
        if (head == null) {
            return;
        }
        System.out.println("pos-order");
        Stack<BtNode> stack = new Stack<>();
        Stack<BtNode> help = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            help.push(head);
//            System.out.print(head.value + " ");
            if (head.left != null) {
                stack.push(head.left);
            }
            if (head.right != null) {
                stack.push(head.right);
            }
        }
//        System.out.println("helpSize" + help.size());
        while (!help.isEmpty()) {
            System.out.print(help.pop().value + " ");
        }
    }

    public static void posPro(BtNode h){
        if(h == null){
            return ;
        }
        Stack<BtNode> stack = new Stack<>();
        BtNode c = null;
        stack.push(h);
        while(!stack.isEmpty()){
            c = stack.peek();
            if(c.left != null && c.left != h && c.right != h){
                stack.push(c.left);
            }else if(c.right != null && c.right != h){
                stack.push(c.right);
            }else{
                System.out.print(stack.pop().value + " ");
                h = c;
            }
        }
    }

    public static void main(String[] args) {
        BtNode node = Tools.createSimpleBtNode();
        pre(node);
        System.out.println();
        System.out.println("===========");
        in(node);
        System.out.println();
        System.out.println("===========");
        pos(node);
        System.out.println();
        System.out.println("===========");
        posPro(node);
    }
}
