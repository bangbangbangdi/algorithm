package my_practise.p2023.basic.datastructure.Graph;

import java.util.ArrayList;
import java.util.Set;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.Graph
 * className:      Node
 * author:     BangDi
 * description:  点
 * date:    2023/6/9 16:17
 * version:    1.0
 */
public class Node {

    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }


}
