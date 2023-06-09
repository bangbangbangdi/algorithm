package my_practise.p2023.basic.datastructure.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.Graph
 * className:      Graph
 * author:     BangDi
 * description:  图
 * date:    2023/6/9 16:18
 * version:    1.0
 */
public class Graph {

    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
