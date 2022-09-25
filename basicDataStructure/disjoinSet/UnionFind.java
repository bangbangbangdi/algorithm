package basicDataStructure.disjoinSet;

import myClass10.Code01_UnionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

// 有若干个样本a、b、c、d…类型假设是V
// 在并查集中一开始认为每个样本都在单独的集合里
// 用户可以在任何时候调用如下两个方法：
// boolean isSameSet(V x, V y) : 查询样本x和样本y是否属于一个集合
// void union(V x, V y) : 把x和y各自所在集合的所有样本合并成一个集合
// isSameSet和union方法的代价越低越好

public class UnionFind {

    public static class Node<V> {
        public V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionSet<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizes;

        public UnionSet(List<V> list) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizes = new HashMap<>();
            for (V v : list) {
                Node<V> node = new Node<>(v);
                nodes.put(v, node);
                parents.put(node, node);
                sizes.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> stack = new Stack<>();
            while (cur != parents.get(cur)) {
                stack.push(cur);
                cur = parents.get(cur);
            }

            while (!stack.isEmpty()) {
                parents.put(stack.pop(), cur);
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
            if (!nodes.containsKey(x) || !nodes.containsKey(y) || isSameSet(x, y)) {
                return;
            }
            Integer xSize = sizes.get(parents.get(nodes.get(x)));
            Integer ySize = sizes.get(parents.get(nodes.get(y)));

            Node<V> bigHead = xSize > ySize ? parents.get(nodes.get(x)) : parents.get(nodes.get(y));
            Node<V> smallHead = bigHead == parents.get(nodes.get(x)) ? parents.get(nodes.get(y)) : parents.get(nodes.get(x));

            parents.put(smallHead, bigHead);
            sizes.put(bigHead, xSize + ySize);
            sizes.remove(smallHead);
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
