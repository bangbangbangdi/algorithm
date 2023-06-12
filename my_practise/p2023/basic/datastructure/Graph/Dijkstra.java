package my_practise.p2023.basic.datastructure.Graph;

import java.util.*;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure.Graph
 * className:      Dijkstra
 * author:     BangDi
 * description:
 * 1）Dijkstra算法必须指定一个源点
 * 2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，源点到其他所有点的最小距离都为正无穷大
 * 3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
 * 4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了
 * date:    2023/6/9 23:41
 * version:    1.0
 */
public class Dijkstra {

    public static class NodeDistance {
        public Node node;
        public Integer distance;

        public NodeDistance(Node node, Integer distance) {
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
        public HashMap<V, Integer> map;
        public Set<V> selected;
        public int size;
        public ArrayList<V> heap;
        public Comparator<V> comp;

        public Heap(Comparator<V> comp) {
            this.map = new HashMap<>();
            this.selected = new HashSet<>();
            this.size = 0;
            this.heap = new ArrayList<>();
            this.comp = comp;
        }

        public boolean isEmpty() {
            return size <= 0;
        }

        public V pop() {
            V ans = heap.get(0);
            swap(0, --size);
            heapify(0);
            map.remove(ans);
            selected.add(ans);
            return ans;
        }

        public void addOrUpdateOrIgnore(V value) {
            if (selected.contains(value)) {
                return;
            }
            if (map.containsKey(value)) {
                Integer index = map.get(value);
                heap.set(index, value);
                heapInsert(index);
                // 以下这步可省略
                heapify(index);
            } else {
                map.put(value, size);
                heap.add(size, value);
                size++;
            }
        }

        public void heapInsert(int index) {
            while (comp.compare(heap.get(index), heap.get(index / 2)) > 0) {
                swap(index, index / 2);
                index = index / 2;
            }
        }

        public void heapify(int index) {
            int leftIndex = index * 2 + 1;
            while (leftIndex < size) {
                int largestIndex = leftIndex + 1 < size && comp.compare(heap.get(leftIndex), heap.get(leftIndex + 1)) < 0 ? leftIndex + 1 : leftIndex;
                largestIndex = comp.compare(heap.get(largestIndex), heap.get(index)) > 0 ? largestIndex : index;
                if (largestIndex == index) {
                    return;
                }
                swap(index, largestIndex);
                index = largestIndex;
                leftIndex = index * 2 + 1;
            }
        }

        public void swap(int i, int j) {
            if (i == j) {
                return;
            }
            V tmp = heap.get(i);
            heap.set(i, heap.get(j));
            map.put(heap.get(i), i);
            heap.set(j, tmp);
            map.put(heap.get(j), j);
        }
    }

    public static HashMap<Node, Integer> dijkstra(Graph graph, Node from) {
        HashMap<Node, Integer> ans = new HashMap<>();
        HashMap<Node, NodeDistance> map = new HashMap<>();
        Comparator<NodeDistance> comp = new Comparator<NodeDistance>() {
            @Override
            public int compare(NodeDistance o1, NodeDistance o2) {
                return o2.distance - o1.distance;
            }
        };
        Heap<NodeDistance> heap = new Heap<>(comp);
        for (Node node : graph.nodes.values()) {
            if (node == from) {
                map.put(node, new NodeDistance(node, 0));
                heap.addOrUpdateOrIgnore(map.get(node));
                continue;
            }
            map.put(node,new NodeDistance(node,Integer.MAX_VALUE));
            heap.addOrUpdateOrIgnore(map.get(node));
        }
        while(!heap.isEmpty()){
            NodeDistance pop = heap.pop();

        return ans;
    }

//    public static void test() {
//        Node n1 = new Node(1);
//        Node n2 = new Node(2);
//        Node n3 = new Node(3);
//        Node n4 = new Node(4);
//        Node n5 = new Node(5);
//
//        Edge e1 = new Edge(n1, n2, 1);
//        Edge e6 = new Edge(n1, n3, 6);
//        Edge e2 = new Edge(n2, n3, 2);
//        Edge e3 = new Edge(n2, n4, 3);
//        Edge e4 = new Edge(n2, n5, 4);
//        Edge e10 = new Edge(n3, n5, 10);
//
//        n1.nexts.add(n2);
//        n1.nexts.add(n3);
//        n2.nexts.add(n3);
//        n2.nexts.add(n4);
//        n3.nexts.add(n5);
//        n2.nexts.add(n5);
//
//        n1.edges.add(e1);
//        n1.edges.add(e6);
//        n2.edges.add(e2);
//        n2.edges.add(e3);
//        n2.edges.add(e4);
//        n3.edges.add(e10);
//
//        Graph graph = new Graph();
//        graph.nodes.put(1, n1);
//        graph.nodes.put(2, n2);
//        graph.nodes.put(3, n3);
//        graph.nodes.put(4, n4);
//        graph.nodes.put(5, n5);
//
//        graph.edges.add(e1);
//        graph.edges.add(e2);
//        graph.edges.add(e3);
//        graph.edges.add(e4);
//        graph.edges.add(e6);
//        graph.edges.add(e10);
//
//        HashMap<Node, Integer> res = dijkstra(graph, n1);
//        for (Node node : res.keySet()) {
//            System.out.println(node.value + " : " + res.get(node));
//        }
//
////        NodeRecord<Node> re1 = new NodeRecord<>(n1, 5);
////        NodeRecord<Node> re2 = new NodeRecord<>(n2, 4);
////        NodeRecord<Node> re3 = new NodeRecord<>(n3, 100);
////
////        Heap<NodeRecord<Node>> heap = new Heap<>(3, new NodeRecordCom());
////        heap.addOrUpdateOrIgnore(re1);
////        heap.addOrUpdateOrIgnore(re2);
////        heap.addOrUpdateOrIgnore(re3);
////        re3.setDistance(1);
////        heap.addOrUpdateOrIgnore(re3);
////
////        NodeRecord<Node> p1 = heap.pop();
////        NodeRecord<Node> p2 = heap.pop();
////        NodeRecord<Node> p3 = heap.pop();
////
////        System.out.println(p1.node.value + " : " + p1.distance);
////        System.out.println(p2.node.value + " : " + p2.distance);
////        System.out.println(p3.node.value + " : " + p3.distance);
//    }
//
//    public static void main(String[] args) {
//        test();
//    }


}
