package myClass10;

import java.util.*;

// Depth First Search
public class Code02_DFS {

    public static void dfs(Node node) {
        if (node == null){
            return ;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for(Node next:cur.nexts){
                if (!set.contains(next)){
                    set.add(next);
                    stack.add(cur);
                    stack.add(next);
                    System.out.println(cur.value);
                    break;
                }
            }
        }
    }
}