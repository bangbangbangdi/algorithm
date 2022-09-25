package basicDataStructure.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFS {

    public static void dfs(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(head);
        set.add(head);
        System.out.println(head.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    System.out.println(next.value);
                    set.add(next);
                    stack.push(head);
                    stack.push(next);
                    break;
                }
            }
        }

    }
    public static void test(){
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        n1.nexts.add(n2);
        n1.nexts.add(n3);
        n1.nexts.add(n4);
        n2.nexts.add(n5);
        n4.nexts.add(n6);
        n5.nexts.add(n1);
        dfs(n1);
    }

    public static void main(String[] args) {
        test();
    }
}
