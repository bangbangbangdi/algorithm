package my_practise.p2023.basic.datastructure.Graph;

import basicDataStructure.disjoinSet.UnionFind;

import java.util.*;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.Graph
 * className:      Kruskal
 * author:     BangDi
 * description:  给一个图,返回最小生成树的边集
 * 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
 * 2）当前的边要么进入最小生成树的集合，要么丢弃
 * 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
 * 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
 * 5）考察完所有边之后，最小生成树的集合也得到了
 * date:    2023/6/13 11:01
 * version:    1.0
 */
public class Kruskal {

    public static ArrayList<Edge> kruskal(Graph graph) {
        if (graph == null) {
            return null;
        }
        ArrayList<Edge> ans = new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<>(graph.nodes.values());
        UnionFind.UnionSet<Node> unionSet = new UnionFind.UnionSet<>(nodes);
        PriorityQueue<Edge> heap = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.value - o2.value;
            }
        });
        heap.addAll(graph.edges);
        while (!heap.isEmpty()) {
            Edge edge = heap.poll();
            Node from = edge.from;
            Node to = edge.to;
            if (!unionSet.isSameSet(from, to)) {
                unionSet.union(from, to);
                ans.add(edge);
            }
        }
        return ans;
    }

    public static void test() {
        Graph graph = new Graph();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        Edge e1 = new Edge(n1, n2, 1);
        Edge e2 = new Edge(n2, n3, 2);
        Edge e3 = new Edge(n3, n4, 3);
        Edge e4 = new Edge(n2, n4, 4);
        Edge e10 = new Edge(n4, n5, 10);
        Edge e50 = new Edge(n4, n6, 50);
        Edge e70 = new Edge(n5, n6, 70);
        Edge e20 = new Edge(n1, n6, 20);

        n1.nexts.add(n2);
        n1.nexts.add(n5);
        n2.nexts.add(n3);
        n2.nexts.add(n4);
        n3.nexts.add(n4);
        n4.nexts.add(n5);
        n6.nexts.add(n4);
        n6.nexts.add(n5);

        graph.nodes.put(1, n1);
        graph.nodes.put(2, n2);
        graph.nodes.put(3, n3);
        graph.nodes.put(4, n4);
        graph.nodes.put(5, n5);
        graph.nodes.put(6, n6);

        graph.edges.add(e1);
        graph.edges.add(e2);
        graph.edges.add(e3);
        graph.edges.add(e4);
        graph.edges.add(e10);
        graph.edges.add(e50);
        graph.edges.add(e70);
        graph.edges.add(e20);

        List<Edge> kruskal = kruskal(graph);
        for (Edge edge : kruskal) {
            System.out.println(edge.value);
        }
    }

    public static void main(String[] args) {
        test();
    }
}
