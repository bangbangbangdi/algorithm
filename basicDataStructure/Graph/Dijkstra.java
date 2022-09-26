package basicDataStructure.Graph;

// 1）Dijkstra算法必须指定一个源点
// 2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，源点到其他所有点的最小距离都为正无穷大
// 3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
// 4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class Dijkstra {

    public static class NodeRecord<V> {
        public V node;
        public int distance;

        public NodeRecord(V node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        public void setDistance(int distance) {
            if (distance < this.distance) {
                this.distance = distance;
            }
        }
    }

    public static class Heap<V> {
        public ArrayList<V> heap;
        public HashMap<V, Integer> map;
        public HashSet<V> selected;
        private final Comparator<V> com;

        public Heap(int size, Comparator<V> com) {
            heap = new ArrayList<>(size);
            map = new HashMap<>();
            selected = new HashSet<>();
            this.com = com;
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }


        public boolean isContains(V node) {
            return map.containsKey(node);
        }

        public boolean isIgnore(V node) {
            return selected.contains(node);
        }

        public V pop() {
            if (heap.isEmpty()) {
                return null;
            }
            swap(0, heap.size() - 1);
            heapify(0, heap.size() - 1);
            V ans = heap.remove(heap.size() - 1);
            map.remove(ans);
            selected.add(ans);
            return ans;
        }

        public void addOrUpdateOrIgnore(V node) {
            if (isIgnore(node)) {
                return;
            }
            if (isContains(node)) {
                Integer index = map.get(node);
                heap.set(index, node);
                heapInsert(index);
            } else {
                heap.add(node);
                map.put(node, heap.size() - 1);
                heapInsert(heap.size() - 1);
            }
        }

        public void heapify(int index, int size) {
            int leftIndex = index * 2 + 1;
            while (leftIndex < size) {
                int largestIndex = leftIndex + 1 < size && (com.compare(heap.get(leftIndex + 1), heap.get(leftIndex)) > 0) ? leftIndex + 1 : leftIndex;
                largestIndex = com.compare(heap.get(index), heap.get(largestIndex)) > 0 ? index : largestIndex;
                if (largestIndex == index) {
                    return;
                }
                swap(largestIndex, index);
                index = largestIndex;
                leftIndex = index * 2 + 1;
            }
        }

        public void heapInsert(int index) {
            while (com.compare(heap.get(index), heap.get((index - 1) / 2)) > 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void swap(int i, int j) {
            V tem = heap.get(i);
            heap.set(i, heap.get(j));
            map.put(heap.get(i), i);
            heap.set(j, tem);
            map.put(heap.get(j), j);
        }
    }

    public static class NodeRecordCom implements Comparator<NodeRecord<Node>> {

        @Override
        public int compare(NodeRecord<Node> o1, NodeRecord<Node> o2) {
            return o2.distance - o1.distance;
        }
    }

    public static HashMap<Node, Integer> dijkstra(Graph graph, Node from) {
        if (graph == null || from == null || !graph.nodes.containsValue(from)) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>(graph.nodes.values());
        HashMap<Node, NodeRecord<Node>> map = new HashMap<>();
        Heap<NodeRecord<Node>> heap = new Heap<>(arr.size(), new NodeRecordCom());
        HashMap<Node, Integer> ans = new HashMap<>();
        ArrayList<NodeRecord> list = new ArrayList<>();

        for (Node node : arr) {
            if (node == from) {
                map.put(node, new NodeRecord<>(node, 0));
                continue;
            }
            map.put(node, new NodeRecord<>(node, Integer.MAX_VALUE));
        }
        for (NodeRecord<Node> nodeR : map.values()) {
            heap.addOrUpdateOrIgnore(nodeR);
        }

        while (!heap.isEmpty()) {
            NodeRecord<Node> pop = heap.pop();
            ans.put(pop.node, pop.distance);
            list.add(pop);
            for (Edge edge : pop.node.edges) {
                Node to = edge.to;
                NodeRecord<Node> record = map.get(to);
                record.setDistance(pop.distance + edge.weight);
                heap.addOrUpdateOrIgnore(record);
            }
        }

        for (NodeRecord<Node> node : list) {
            System.out.println(node.node.value + " : " + node.distance);
        }
        System.out.println();

        return ans;
    }

    public static void test() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        Edge e1 = new Edge(n1, n2, 1);
        Edge e6 = new Edge(n1, n3, 6);
        Edge e2 = new Edge(n2, n3, 2);
        Edge e3 = new Edge(n2, n4, 3);
        Edge e4 = new Edge(n2, n5, 4);
        Edge e10 = new Edge(n3, n5, 10);

        n1.nexts.add(n2);
        n1.nexts.add(n3);
        n2.nexts.add(n3);
        n2.nexts.add(n4);
        n3.nexts.add(n5);
        n2.nexts.add(n5);

        n1.edges.add(e1);
        n1.edges.add(e6);
        n2.edges.add(e2);
        n2.edges.add(e3);
        n2.edges.add(e4);
        n3.edges.add(e10);

        Graph graph = new Graph();
        graph.nodes.put(1, n1);
        graph.nodes.put(2, n2);
        graph.nodes.put(3, n3);
        graph.nodes.put(4, n4);
        graph.nodes.put(5, n5);

        graph.edges.add(e1);
        graph.edges.add(e2);
        graph.edges.add(e3);
        graph.edges.add(e4);
        graph.edges.add(e6);
        graph.edges.add(e10);

        HashMap<Node, Integer> res = dijkstra(graph, n1);
        for (Node node : res.keySet()) {
            System.out.println(node.value + " : " + res.get(node));
        }

//        NodeRecord<Node> re1 = new NodeRecord<>(n1, 5);
//        NodeRecord<Node> re2 = new NodeRecord<>(n2, 4);
//        NodeRecord<Node> re3 = new NodeRecord<>(n3, 100);
//
//        Heap<NodeRecord<Node>> heap = new Heap<>(3, new NodeRecordCom());
//        heap.addOrUpdateOrIgnore(re1);
//        heap.addOrUpdateOrIgnore(re2);
//        heap.addOrUpdateOrIgnore(re3);
//        re3.setDistance(1);
//        heap.addOrUpdateOrIgnore(re3);
//
//        NodeRecord<Node> p1 = heap.pop();
//        NodeRecord<Node> p2 = heap.pop();
//        NodeRecord<Node> p3 = heap.pop();
//
//        System.out.println(p1.node.value + " : " + p1.distance);
//        System.out.println(p2.node.value + " : " + p2.distance);
//        System.out.println(p3.node.value + " : " + p3.distance);
    }

    public static void main(String[] args) {
        test();
    }

}
