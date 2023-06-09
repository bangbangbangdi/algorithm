package my_practise.p2023.basic.datastructure.Graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.Graph
 * className:      DFS
 * author:     BangDi
 * description:  深度优先遍历
 * date:    2023/6/9 17:12
 * version:    1.0
 */
public class DFS {

    public static void dfs(Node node){
        if (node == null){
            return ;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.println(node.value + " ");
        while(!stack.isEmpty()){
            Node pop = stack.pop();
            for (Node next : pop.nexts) {
                if (!set.contains(next)){
                    System.out.println(next.value + " ");
                    set.add(next);
                    stack.push(pop);
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
