package my_practise.p2023.basic.datastructure;

import tool.BtNode;
import tool.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure
 * className:      MaxSubBSTSize
 * author:     BangDi
 * description:  TODO
 * date:    2023/6/4 20:07
 * version:    1.0
 */
public class MaxSubBSTSize {

    public static int maxSubBSTSize(BtNode node) {
        if (node == null) {
            return 0;
        }
        return process(node).maxBSTSize;
    }

    public static Info process(BtNode node) {
        if (node == null) {
            return null;
        }
        Info left = process(node.left);
        Info right = process(node.right);
        int maxValue = node.value;
        int minValue = node.value;
        boolean isBST = true;
        int maxBSTSize = 1;
        if (left != null) {
            maxValue = Math.max(maxValue, left.maxValue);
            minValue = Math.min(minValue, left.minValue);
            isBST = left.isBST && node.value > left.maxValue;
            maxBSTSize = left.maxBSTSize;
        }
        if (right != null) {
            maxValue = Math.max(maxValue, right.maxValue);
            minValue = Math.min(minValue, right.minValue);
            isBST = isBST && right.isBST && node.value < right.minValue;
            maxBSTSize = Math.max(maxBSTSize, right.maxBSTSize);
        }
        if (isBST) {
            maxBSTSize = 1;
            if (left != null) {
                maxBSTSize += left.maxBSTSize;
            }
            if (right != null) {
                maxBSTSize += right.maxBSTSize;
            }
        }
        return new Info(maxValue, minValue, isBST, maxBSTSize);
    }

    public static class Info {
        int maxValue;
        int minValue;
        boolean isBST;
        int maxBSTSize;

        public Info(int maxValue, int minValue, boolean isBST, int maxBSTSize) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.isBST = isBST;
            this.maxBSTSize = maxBSTSize;
        }
    }

    public static int compare(BtNode node) {
        if (node == null) {
            return 0;
        }
        return process2(node);
    }

    public static int process2(BtNode node) {
        if (node == null) {
            return 0;
        }
        if (getBSTSize(node) != 0) {
            return getBSTSize(node);
        }
        int left = process2(node.left);
        int right = process2(node.right);
        return Math.max(left, right);
    }

    public static int getBSTSize(BtNode node) {
        if (node == null) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        inList(node, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return 0;
            }
        }
        return list.size();
    }

    public static void inList(BtNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inList(node.left, list);
        list.add(node.value);
        inList(node.right, list);
    }

    // for test
    public static BtNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static BtNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        BtNode head = new BtNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        boolean succeed = true;
        for (int i = 0; i < testTimes && succeed; i++) {
            BtNode head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize(head) != compare(head)) {
                succeed = false;
                Tools.printBinaryTree(head);
                System.out.println("maxSubBSTSize(head) = " + maxSubBSTSize(head));
                System.out.println("compare(head) = " + compare(head));
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
//        test();
    }
}
