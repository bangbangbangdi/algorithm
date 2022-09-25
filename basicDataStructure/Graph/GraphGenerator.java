package basicDataStructure.Graph;


public class GraphGenerator {

    // matrix 所有的边
    // N*3 的矩阵
    // [from节点上面的值,to节点上面的值,weight]
    public static Graph createGraph(Integer[][] matrix) {
        if (matrix == null || matrix.length == 0) {
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
            graph.edges.add(edge);
            from.out++;
            from.nexts.add(to);
            from.edges.add(edge);
            to.in++;
        }
        return graph;
    }

}
