package my_practise.p2023.basic.datastructure.disjoinSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure.disjoinSet
 * className:      UnionFind
 * author:     BangDi
 * description:  并查集
 * // 有若干个样本a、b、c、d…类型假设是V
 * // 在并查集中一开始认为每个样本都在单独的集合里
 * // 用户可以在任何时候调用如下两个方法：
 * // boolean isSameSet(V x, V y) : 查询样本x和样本y是否属于一个集合
 * // void union(V x, V y) : 把x和y各自所在集合的所有样本合并成一个集合
 * // isSameSet和union方法的代价越低越好
 * date:    2023/6/7 23:48
 * version:    1.0
 */
public class UnionFind {

    // 该操作除了可以在Node层再加一些信息外看不出有什么特别的必要
    public static class Node<V> {
        public V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionSet<V> {
        private HashMap<V, Node<V>> nodes;
        private HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> size;

        public UnionSet(List<V> list) {
            if (list == null) {
                return;
            }
            nodes = new HashMap<>();
            parents = new HashMap<>();
            size = new HashMap<>();
            for (V v : list) {
                Node<V> node = new Node<>(v);
                nodes.put(v, node);
                parents.put(node, node);
                size.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> stack = new Stack<>();
            if (cur != parents.get(cur)) {
                stack.push(cur);
                cur = parents.get(cur);
            }
            while (!stack.isEmpty()) {
                Node<V> pop = stack.pop();
                parents.put(pop, cur);
            }
            return cur;
        }

        public boolean isSameSet(V x, V y) {
            if (!nodes.containsKey(x) || !nodes.containsKey(y)) {
                return false;
            }
            Node<V> nodeX = nodes.get(x);
            Node<V> nodeY = nodes.get(y);
            return findFather(nodeX) == findFather(nodeY);
        }

        public void union(V x, V y) {
            if (!nodes.containsKey(x) || !nodes.containsKey(y) || isSameSet(x, y)) {
                return;
            }
            Integer xSize = size.get(findFather(nodes.get(x)));
            Integer ySize = size.get(findFather(nodes.get(y)));
            Node<V> big = xSize > ySize ? findFather(nodes.get(x)) : findFather(nodes.get(y));
            Node<V> small = big == findFather(nodes.get(x)) ? findFather(nodes.get(y)) : findFather(nodes.get(x));
            parents.put(small, big);
            size.put(big, xSize + ySize);
            size.remove(small);
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
        System.out.println(unionSet.isSameSet(1, 2));
        unionSet.union(1, 2);
        System.out.println(unionSet.isSameSet(1, 2));
    }
}
