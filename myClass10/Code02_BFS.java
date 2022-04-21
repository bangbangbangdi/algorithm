package myClass10;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// Breadth First Search
public class Code02_BFS {

    // 从某个node出发
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        set.add(node);
        queue.add(node);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.value);
            for (Node next : poll.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }
}
