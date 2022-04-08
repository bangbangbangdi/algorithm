package myClass07;


import tool.BtNode;
import tool.Tools;

import java.util.LinkedList;
import java.util.Queue;

public class Code04_SerializeAndReconstructTree {

    public static Queue<String> preSerialize(BtNode head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    public static void pres(BtNode head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }

    public static BtNode buildByPreQueue(Queue<String> preList) {
        if (preList == null) {
            return null;
        }

        return preB(preList);
    }

    public static BtNode preB(Queue<String> preList) {
        String value = preList.poll();
        if (value == null) {
            return null;
        }
        BtNode node = new BtNode(Integer.parseInt(value));
        node.left = preB(preList);
        node.right = preB(preList);
        return node;
    }

    public static Queue<String> levelSerial(BtNode head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            return null;
        }
        Queue<BtNode> queue = new LinkedList<>();
        ans.add(String.valueOf(head.value));
        queue.add(head);
        while (!queue.isEmpty()) {
            BtNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
                ans.add(String.valueOf(node.left.value));
            } else {
                ans.add(null);
            }
            if (node.right != null) {
                queue.add(node.right);
                ans.add(String.valueOf(node.right.value));
            } else {
                ans.add(null);
            }
        }
        return ans;
    }

    public static BtNode buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        String value = levelList.poll();
        BtNode head = generateNode(value);
        Queue<BtNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            BtNode node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }

    public static BtNode generateNode(String value) {
        if (value == null) {
            return null;
        }
        return new BtNode(Integer.parseInt(value));
    }


    public static void main(String[] args) {
        int testTimes = 500000;
        int maxLevel = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; (i < testTimes) && succeed; i++) {
            BtNode head = Tools.generateRandomBST(maxLevel, maxValue);
            Queue<String> preList = preSerialize(head);
            BtNode preHead = buildByPreQueue(preList);
            Queue<String> levelList = levelSerial(head);
            BtNode levelHead = buildByLevelQueue(levelList);
            if (!Tools.isSameValueStructure(head, preHead) || !Tools.isSameValueStructure(head, levelHead)) {
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }
}
