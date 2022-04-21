package myClass08;

import tool.BtNode;
import tool.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Code08_MaxDistance {

    public static int maxDistance(BtNode head) {
        if (head == null) {
            return 0;
        }

        return process1(head).maxDistance;
    }

    public static class Info {
        public int oneMaxDistance;
        public int maxDistance;

        public Info(int oneMaxDistance, int maxDistance) {
            this.oneMaxDistance = oneMaxDistance;
            this.maxDistance = maxDistance;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "oneMaxDistance=" + oneMaxDistance +
                    ", maxDistance=" + maxDistance +
                    '}';
        }
    }

    public static Info process1(BtNode head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process1(head.left);
        Info rightInfo = process1(head.right);

        int oneMaxDistance = Math.max(leftInfo.oneMaxDistance, rightInfo.oneMaxDistance) + 1;

        int maxDistance = leftInfo.oneMaxDistance + rightInfo.oneMaxDistance + 1;

        maxDistance = Math.max(Math.max(maxDistance, leftInfo.maxDistance), rightInfo.maxDistance);
        Info info = new Info(oneMaxDistance, maxDistance);
//        System.out.println(head.value + " " + info);
        return info;
    }


    public static int maxDistance2(BtNode head) {
        if (head == null) {
            return 0;
        }
        int max = 0;
        ArrayList<BtNode> arr = new ArrayList<>();
        getPreList(head, arr);
        HashMap<BtNode, BtNode> parentMap = new HashMap<>();
        getParentMap(head, parentMap);
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.size(); j++) {
                max = Math.max(max, getDistance(arr.get(i), arr.get(j), parentMap));
            }
        }
        return max;
    }


    public static void getPreList(BtNode head, ArrayList<BtNode> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        getPreList(head.left, arr);
        getPreList(head.right, arr);
    }

    public static void getParentMap(BtNode parent, HashMap<BtNode, BtNode> map) {
        if (parent.left != null) {
            map.put(parent.left, parent);
            getParentMap(parent.left, map);
        }
        if (parent.right != null) {
            map.put(parent.right, parent);
            getParentMap(parent.right, map);
        }
    }

    public static BtNode getLowestAncestor(BtNode a, BtNode b, HashMap<BtNode, BtNode> parentMap) {
        BtNode cur = a;
        HashSet<BtNode> parentSet = new HashSet<>();
        while (cur != null) {
            parentSet.add(cur);
            cur = parentMap.get(cur);
        }
        cur = b;
        while (!parentSet.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static int getDistance(BtNode a, BtNode b, HashMap<BtNode, BtNode> parentMap) {
        int distance = 1;
        BtNode lowestAncestor = getLowestAncestor(a, b, parentMap);
        BtNode cur = a;
        while (lowestAncestor != cur) {
            distance++;
            cur = parentMap.get(cur);
        }
        cur = b;
        while (cur != lowestAncestor) {
            distance++;
            cur = parentMap.get(cur);
        }
        return distance;
    }


    public static void main(String[] args) {
        int testTimes = 500000;
        int maxLevel = 6;
        int maxValue = 10;
        boolean succeed = true;
        for (int i = 0; i < testTimes && succeed; i++) {
            BtNode node = Tools.generateRandomBST(maxLevel, maxValue);
            if (maxDistance(node) != maxDistance2(node)) {
                System.out.println(maxDistance(node));
                System.out.println(maxDistance2(node));
                Tools.printBinaryTree(node);
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "F");
//        BtNode node1 = new BtNode(1);
//        BtNode node2 = new BtNode(2);
//        BtNode node3 = new BtNode(3);
//        BtNode node4 = new BtNode(4);
//        BtNode node5 = new BtNode(5);
//
//        node1.left = node2;
//        node2.right = node3;
//        node3.left = node4;
//        node3.right = node5;
//
//        System.out.println(maxDistance(node1));
    }
}
