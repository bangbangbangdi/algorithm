package my_practise.p2023.basic.datastructure.binaryTree;

import tool.BtNode;
import tool.Tools;

import java.util.ArrayList;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure
 * className:      MaxSubBSTHead
 * author:     BangDi
 * description:  TODO
 * date:    2023/6/4 09:39
 * version:    1.0
 */
public class MaxSubBSTHead {

    public static Info maxSubBSTHead(BtNode node) {
        if (node == null) {
            return new Info(0, 0, 0, true, null);
        }
        return process(node);
    }

    public static Info process(BtNode node) {
        if (node == null) {
            return null;
        }
        int maxValue = node.value;
        int minValue = node.value;
        boolean isBST = true;
        int maxBSTSize = 1;
        BtNode maxBSTHead = node;
        Info left = process(node.left);
        Info right = process(node.right);
        if (left != null) {
            maxValue = Math.max(maxValue, left.maxValue);
            minValue = Math.min(minValue, left.minValue);
            isBST = left.isBST && node.value > left.maxValue;
            maxBSTSize = left.maxBSTSize;
            maxBSTHead = left.maxBSTHead;
        }
        if (right != null) {
            maxValue = Math.max(maxValue, right.maxValue);
            minValue = Math.min(minValue, right.minValue);
            isBST = isBST && right.isBST && node.value < right.minValue;
            maxBSTHead = maxBSTSize > right.maxBSTSize ? maxBSTHead : right.maxBSTHead;
            maxBSTSize = Math.max(maxBSTSize, right.maxBSTSize);
        }
        if (isBST) {
            maxBSTHead = node;
            maxBSTSize = 1;
            if (left != null) {
                maxBSTSize += left.maxBSTSize;
            }
            if (right != null) {
                maxBSTSize += right.maxBSTSize;
            }
        }
        return new Info(maxValue, minValue, maxBSTSize, isBST, maxBSTHead);
    }

    public static class Info {
        int maxValue;
        int minValue;
        int maxBSTSize;
        boolean isBST;
        BtNode maxBSTHead;

        public Info(int maxValue, int minValue, int maxBSTSize, boolean isBST, BtNode maxBSTHead) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.maxBSTSize = maxBSTSize;
            this.isBST = isBST;
            this.maxBSTHead = maxBSTHead;
        }
    }

    public static int getBSTSize(BtNode head) {
        if (head == null) {
            return 0;
        }
        ArrayList<BtNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(BtNode head, ArrayList<BtNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static BtNode maxSubBSTHead1(BtNode head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        BtNode leftAns = maxSubBSTHead1(head.left);
        BtNode rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }

    public static int maxSubBSTHead2(BtNode head) {
        if (head == null) {
            return 0;
        }
        if (getBSTSize(head) != 0) {
            return getBSTSize(head);
        }
        int leftAns = maxSubBSTHead2(head.left);
        int rightAns = maxSubBSTHead2(head.right);
        return Math.max(leftAns, rightAns);
    }

    public static void test() {
        BtNode n0 = new BtNode(81);
        BtNode n1 = new BtNode(3);
        BtNode n2 = new BtNode(7);
        BtNode n3 = new BtNode(51);
        BtNode n4 = new BtNode(43);
        BtNode n5 = new BtNode(85);
        BtNode n6 = new BtNode(92);
//        BtNode n7 = new BtNode(8);
//        BtNode n8 = new BtNode(9);
//        n0.left = n1;
        n0.right = n2;
        n2.left = n3;
        n2.right = n4;
        n3.right = n5;
        n4.left = n6;
//        n3.left = n7;
//        n7.left = n8;
//        System.out.println(maxSubBSTHead(n0).maxBSTSize);
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
//            if (maxSubBSTHead1(head) != maxSubBSTHead(head)) {
            if (maxSubBSTHead(head).maxBSTSize != maxSubBSTHead2(head)) {
                succeed = false;
                Tools.printBinaryTree(head);
                System.out.println("maxSubBSTHead(head) = " + maxSubBSTHead(head).maxBSTSize);
                System.out.println("maxSubBSTHead2(head) = " + maxSubBSTHead2(head));
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
//        test();
    }
}
