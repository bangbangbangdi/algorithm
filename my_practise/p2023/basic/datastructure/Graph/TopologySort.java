package my_practise.p2023.basic.datastructure.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure.Graph
 * className:      TopologySort
 * author:     BangDi
 * description:
 * // 1）在图中找到所有入度为0的点输出
 * // 2）把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
 * // 3）图的所有点都被删除后，依次输出的顺序就是拓扑排序
 * // 要求：有向图且其中没有环
 * // 应用：事件安排、编译顺序
 * date:    2023/6/13 20:36
 * version:    1.0
 */
public class TopologySort {

    public static ArrayList<Node> topologySort(Graph graph) {
        if (graph == null) {
            return null;
        }
        ArrayList<Node> ans = new ArrayList<>();
        HashMap<Node, Integer> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            if (node.in == 0) {
                queue.add(node);
                continue;
            }
            map.put(node, node.in);
        }
        while (!queue.isEmpty() && !map.isEmpty()) {
            Node node = queue.poll();
            ans.add(node);
            for (Node next : node.nexts) {
                map.put(next, map.get(next) - 1);
                if (map.get(next) == 0) {
                    queue.add(next);
                }
            }
        }
        while (!queue.isEmpty()) {
            ans.add(queue.poll());
        }
        return ans;
    }

    public static void test() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        n1.nexts.add(n2);
        n1.nexts.add(n3);
        n1.nexts.add(n4);
        n3.nexts.add(n5);
        n4.nexts.add(n2);
        n4.nexts.add(n5);
        n4.nexts.add(n6);
        n6.nexts.add(n2);
        n2.in = 3;
        n3.in = 1;
        n4.in = 1;
        n5.in = 2;
        n6.in = 1;
        Graph graph = new Graph();
        graph.nodes.put(1, n1);
        graph.nodes.put(2, n2);
        graph.nodes.put(3, n3);
        graph.nodes.put(4, n4);
        graph.nodes.put(5, n5);
        graph.nodes.put(6, n6);
        for (Node node : topologySort(graph)) {
            System.out.println(node.value);
        }
    }

    public static void main(String[] args) {
        test();
    }
}