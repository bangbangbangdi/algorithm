package my_practise.p2022.basic.myClass10;

import java.util.*;

public class Code04_Kruskal {

    public static class MySets {
        public HashMap<Node, Set<Node>> setMap;

        public MySets(List<Node> list) {
            for (Node node : list) {
                HashSet<Node> set = new HashSet<>();
                set.add(node);
                setMap.put(node, set);
            }
        }

        public boolean isSameSet(Node from, Node to) {
            return setMap.get(from) == setMap.get(to);
        }

        public void union(Node from, Node to) {
            Set<Node> fromSet = setMap.get(from);
            Set<Node> toSet = setMap.get(to);
            for (Node cur : toSet) {
                fromSet.add(cur);
                setMap.put(cur, fromSet);
            }
        }
    }


    public static class UnionFind {
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSet(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node cur : nodes) {
                fatherMap.put(cur, cur);
                sizeMap.put(cur, 1);
            }
        }

        public Node findFather(Node node) {
            Stack<Node> path = new Stack<>();
            while (fatherMap.get(node) != node) {
                path.add(node);
                node = fatherMap.get(node);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(Node n1, Node n2) {
            return findFather(n1) == findFather(n2);
        }

        public void union(Node n1, Node n2) {
            if (n1 == null || n2 == null || findFather(n1) == findFather(n2)) {
                return;
            }
            Node father1 = findFather(n1);
            Node father2 = findFather(n2);
            Integer size1 = sizeMap.get(father1);
            Integer size2 = sizeMap.get(father2);
            Node big = size1 > size2 ? father1 : father2;
            Node small = big == father1 ? father2 : father1;
            fatherMap.put(small,big);
            sizeMap.put(big,size1 + size2);
            sizeMap.remove(small);
        }
    }

    public static class EdgeComparator implements Comparator<Edge>{
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph){
        PriorityQueue<Edge> edges = new PriorityQueue<>(new EdgeComparator());
        UnionFind unionFind = new UnionFind();
        unionFind.makeSet(graph.nodes.values());
        edges.addAll(graph.edges);
        Set<Edge> ans = new HashSet<>();
        while(!edges.isEmpty()){
            Edge poll = edges.poll();
            Node to = poll.to;
            Node from = poll.from;
            if (!unionFind.isSameSet(to,from)){
                ans.add(poll);
                unionFind.union(to,from);
            }
        }
        return ans;
    }

}
