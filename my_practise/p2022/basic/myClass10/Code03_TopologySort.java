package my_practise.p2022.basic.myClass10;

import java.util.*;

public class Code03_TopologySort {

    public static List<Node> sortedTopology(Graph graph) {
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroQueue = new LinkedList<>();
        for (Node cur : graph.nodes.values()) {
            inMap.put(cur, cur.in);
            if (cur.in == 0) {
                zeroQueue.add(cur);
            }
        }

        ArrayList<Node> result = new ArrayList<>();
        while (!zeroQueue.isEmpty()) {
            Node cur = zeroQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroQueue.add(next);
                }
            }
        }
        return result;
    }

}
