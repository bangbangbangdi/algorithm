package basicDataStructure.binaryTree;

// 1）可以用先序或者中序或者后序或者按层遍历，来实现二叉树的序列化
// 2）用了什么方式序列化，就用什么样的方式反序列化

import tool.BtNode;
import tool.Tools;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SerializeAndReconstructTree {

    public static Queue<Integer> preSerial(BtNode head) {
        if (head == null) {
            return null;
        }
        LinkedList<Integer> ans = new LinkedList<>();
        pre(head, ans);
        return ans;
    }

    public static void pre(BtNode head, Queue<Integer> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(head.value);
            pre(head.left, ans);
            pre(head.right, ans);
        }
    }

    public static BtNode buildByPreQueue(Queue<Integer> queue) {
        if (queue == null) {
            return null;
        }
        return preB(queue);
    }

    public static BtNode preB(Queue<Integer> queue) {
        Integer poll = queue.poll();
        if (poll == null) {
            return null;
        }
        BtNode ans = new BtNode(poll);
        ans.left = preB(queue);
        ans.right = preB(queue);
        return ans;
    }

    public static Stack<Integer> postSerial(BtNode head) {
        if (head == null) {
            return null;
        }
        Stack<Integer> ans = new Stack<>();
        post(head, ans);
        return ans;
    }

    public static void post(BtNode head, Stack<Integer> stack) {
        if (head == null) {
            stack.add(null);
        } else {
            post(head.left, stack);
            post(head.right, stack);
            stack.add(head.value);
        }
    }

    public static BtNode buildByPostStack(Stack<Integer> stack) {
        if (stack == null) {
            return null;
        }
        return postB(stack);
    }

    public static BtNode postB(Stack<Integer> stack) {
        Integer pop = stack.pop();
        if (pop == null) {
            return null;
        }
        BtNode head = new BtNode(pop);
        head.right = postB(stack);
        head.left = postB(stack);
        return head;
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
        Stack<Integer> stack = postSerial(n0);
//        stack.forEach(System.out::print);
        BtNode node = buildByPostStack(stack);
        Tools.printAllBtNode(node);

//        BtNode node = buildByPreQueue(queue);
//        Tools.printAllBtNode(node);
    }

    public static void main(String[] args) {
        test();
    }

}
