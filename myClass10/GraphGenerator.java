package myClass10;

public class GraphGenerator {
    // matrix 所有的边
    // N*3 的矩阵
    // [from节点上面的值,to节点上面的值,weight]
    public static Graph createGraph(Integer[][] matrix){
        Graph graph = new Graph();

        for (int i = 0; i < matrix.length; i++) {
            Integer from = matrix[i][0];
            Integer to = matrix[i][1];
            Integer weight = matrix[i][2];
            if (!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));
            }
            if (!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Node(to));
            }
            Node toNode = graph.nodes.get(to);
            Node fromNode = graph.nodes.get(from);
            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(edge);
            graph.edges.add(edge);

        }

        return graph;
    }
}
