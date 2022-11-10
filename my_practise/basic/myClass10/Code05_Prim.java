package my_practise.basic.myClass10;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Code05_Prim {

    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {
        PriorityQueue<Edge> heap = new PriorityQueue<>(new EdgeComparator());
        HashSet<Edge> edgeSet = new HashSet<>();
        HashSet<Node> nodeSet = new HashSet<>();

        for (Node node : graph.nodes.values()) { // 防森林
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                heap.addAll(node.edges);
                while (!heap.isEmpty()) {
                    Edge edge = heap.poll();
                    Node to = edge.to;
                    if (!nodeSet.contains(to)) {
                        nodeSet.add(to);
                        edgeSet.add(edge);
                        heap.addAll(to.edges);
                    }
                }
            }
        }
        return edgeSet;
    }

}
