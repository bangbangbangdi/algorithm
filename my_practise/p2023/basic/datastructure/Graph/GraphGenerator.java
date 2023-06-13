package my_practise.p2023.basic.datastructure.Graph;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.Graph
 * className:      GraphGenerator
 * author:     BangDi
 * description:  图的转换接口
 * // matrix 所有的边
 * // N*3 的矩阵
 * // [from节点上面的值,to节点上面的值,weight]
 * public static Graph createGraph(Integer[][] matrix) {
 * if (matrix == null || matrix.length == 0) {
 * date:    2023/6/13 9:54
 * version:    1.0
 */
public class GraphGenerator {

    public static Graph generator(Integer[][] matrix) {
        if (matrix == null || matrix.length < 1) {
            return new Graph();
        }
        Graph graph = new Graph();
        for (Integer[] arr : matrix) {
            if (!graph.nodes.containsKey(arr[0])) {
                graph.nodes.put(arr[0], new Node(arr[0]));
            }
            if (!graph.nodes.containsKey(arr[1])) {
                graph.nodes.put(arr[1], new Node(arr[1]));
            }
            Node from = graph.nodes.get(arr[0]);
            Node to = graph.nodes.get(arr[1]);
            Edge edge = new Edge(from, to, arr[2]);
            from.out++;
            from.nexts.add(to);
            from.edges.add(edge);
            to.in++;
            graph.edges.add(edge);
        }

        return graph;
    }

}