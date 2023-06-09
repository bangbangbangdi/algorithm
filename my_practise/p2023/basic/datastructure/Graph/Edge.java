package my_practise.p2023.basic.datastructure.Graph;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.Graph
 * className:      Edge
 * author:     BangDi
 * description:  边
 * date:    2023/6/9 16:17
 * version:    1.0
 */
public class Edge {

    public int value;
    public Node from;
    public Node to;

    public Edge(int value, Node from, Node to) {
        this.value = value;
        this.from = from;
        this.to = to;
    }
}