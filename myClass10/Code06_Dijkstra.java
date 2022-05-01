package myClass10;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class Code06_Dijkstra {

    public static HashMap<Node, Integer> dijkstra(Node head) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        HashSet<Node> selectedNodes = new HashSet<>();
        distanceMap.put(head, 0);
        Node minNode = getMinDistanceAndUnselected(distanceMap, selectedNodes);
        while (minNode != null) {
            Integer distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node to = edge.to;
                if (!distanceMap.containsKey(to)) {
                    distanceMap.put(to, distance + edge.weight);
                } else {
                    distanceMap.put(to, Math.min(distance + edge.weight, distanceMap.get(to)));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselected(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    public static Node getMinDistanceAndUnselected(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNode) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNode.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }


    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        public Node[] heap;
        public int size;
        public HashMap<Node, Integer> heapIndexMap;
        public HashMap<Node, Integer> distanceMap;

        public NodeHeap(int size) {
            this.heap = new Node[size];
            this.heapIndexMap = new HashMap<>();
            this.distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (!distanceMap.containsKey(node)) {
                heap[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(size++);
            } else if (distanceMap.get(node) != -1) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(heapIndexMap.get(node));
            }
        }

        public NodeRecord pop() {
            NodeRecord record = new NodeRecord(heap[0], distanceMap.get(heap[0]));
            swap(0, size - 1);
            heapIndexMap.put(heap[size - 1], -1);
            distanceMap.put(heap[size - 1], -1);
            heap[size - 1] = null;
            heapify(0,--size);
            return record;
        }

        public void insertHeapify(int index) {
            while (distanceMap.get(heap[index]) < distanceMap.get(heap[index / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapify(int index,int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(heap[left]) > distanceMap.get(heap[left + 1]) ? left + 1 : left;
                smallest = distanceMap.get(heap[smallest]) < distanceMap.get(heap[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(index, smallest);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        public void swap(int i, int j) {
            Node iNode = heap[i];
            Node jNode = heap[j];

            heap[i] = jNode;
            heap[j] = iNode;

            heapIndexMap.put(iNode, j);
            heapIndexMap.put(jNode, i);
        }

    }

    public static HashMap<Node,Integer> dijkstra2(Node node,int size){
        HashMap<Node, Integer> ans = new HashMap<>();
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(node,0);
        while(!nodeHeap.isEmpty()){
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : node.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to,distance + edge.weight);
            }
            ans.put(node, distance);
        }
        return ans;
    }
}