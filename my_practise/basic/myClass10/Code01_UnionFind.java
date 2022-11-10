package my_practise.basic.myClass10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code01_UnionFind {
    public static class Node<V> {
        public V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionSet<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionSet(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (parents.get(cur) != cur) {
                path.push(cur);
                cur = parents.get(cur);
            }
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V x, V y) {
            if (!nodes.containsKey(x) || !nodes.containsKey(y)) {
                return false;
            }
            return findFather(nodes.get(x)) == findFather(nodes.get(y));
        }

        public void union(V x, V y) {
            if (!nodes.containsKey(x) || !nodes.containsKey(y)) {
                return;
            }
            Node<V> xFather = findFather(nodes.get(x));
            Node<V> yFather = findFather(nodes.get(y));
            if (xFather != yFather) {
                Node<V> big = sizeMap.get(xFather) >= sizeMap.get(yFather) ? xFather : yFather;
                Node<V> small = big == xFather ? yFather : xFather;

                parents.put(small, big);
                sizeMap.put(big, sizeMap.get(big) + sizeMap.get(small));
                sizeMap.remove(small);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Integer int1 = 1;
        Integer int2 = 2;
        Integer int3 = 3;
        Integer int4 = 4;
        Integer int5 = 5;
        arr.add(int1);
        arr.add(int2);
        arr.add(int3);
        arr.add(int4);
        arr.add(int5);
        UnionSet<Integer> unionSet = new UnionSet<>(arr);
        System.out.println(unionSet.isSameSet(1,2));
        unionSet.union(1,2);
        System.out.println(unionSet.isSameSet(1,2));
    }
}