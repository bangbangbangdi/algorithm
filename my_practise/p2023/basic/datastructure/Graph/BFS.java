package my_practise.p2023.basic.datastructure.Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.Graph
 * className:      BFS
 * author:     BangDi
 * description:
 // 从给定节点出发,对图进行宽度优先遍历
 * date:    2023/6/9 15:49
 * version:    1.0
 */

// 非常简单的一道题，稍微注意一下环的问题就好了
public class BFS {

    public static void bfs(Node node){
        if (node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        queue.add(node);
        stack.push(node);
        while(!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.print(poll.value + " ");
            for (Node next : poll.nexts) {
                if (!stack.contains(next)){
                    stack.push(next);
                    queue.add(next);
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
        bfs(n1);
    }

    public static void main(String[] args) {
        test();
    }
}
