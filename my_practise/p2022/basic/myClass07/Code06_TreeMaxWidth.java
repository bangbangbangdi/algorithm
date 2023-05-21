package my_practise.p2022.basic.myClass07;

import tool.BtNode;
import tool.Tools;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Code06_TreeMaxWidth {

    public static int maxWidthUseMap(BtNode head) {
        if (head == null) {
            return 0;
        }
        Queue<BtNode> queue = new LinkedList<>();
        queue.add(head);
        HashMap<BtNode, Integer> map = new HashMap<>();
        map.put(head, 1);
        int max = 0;
        int curLevel = 1;
        int curLevelNode = 0;
        while (!queue.isEmpty()) {
            BtNode node = queue.poll();
            int curNodeLevel = map.get(node);
            if (node.left != null) {
                queue.add(node.left);
                map.put(node.left, curNodeLevel + 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                map.put(node.right, curNodeLevel + 1);
            }
            if (curNodeLevel == curLevel) {
                curLevelNode++;
            } else {
                max = Math.max(max, curLevelNode);
                curLevelNode = 1;
                curLevel++;
            }
        }
        max = Math.max(max, curLevelNode);
        return max;
    }

    public static int maxWidthNoMap(BtNode head) {
        if (head == null) {
            return 0;
        }
        Queue<BtNode> queue = new LinkedList<>();
        int max = 0;
        int curNodeNum = 0;
        BtNode curEnd = head;
        BtNode nextEnd = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            BtNode curNode = queue.poll();
            if (curNode.left != null) {
                queue.add(curNode.left);
                nextEnd = curNode.left;
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
                nextEnd = curNode.right;
            }
            curNodeNum++;
            if (curNode == curEnd) {
                max = Math.max(max, curNodeNum);
                curNodeNum = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int testTimes = 500000;
        int maxLevel = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; (i < testTimes) && succeed; i++) {
            BtNode node = Tools.generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(node) != maxWidthNoMap(node)) {
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }
}
