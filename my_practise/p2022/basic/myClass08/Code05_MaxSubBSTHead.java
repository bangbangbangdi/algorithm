package my_practise.p2022.basic.myClass08;


import tool.BtNode;
import tool.Tools;

import java.util.ArrayList;

public class Code05_MaxSubBSTHead {
    public static BtNode getMaxSubBSTHead(BtNode head) {
        if (head == null) {
            return null;
        }
        return process(head).maxBSTHead;
    }

    public static class Info {
        public int max;
        public int min;
        public boolean isBST;
        public int maxBSTSize;
        public BtNode maxBSTHead;

        @Override
        public String toString() {
            return "Info{" +
                    "max=" + max +
                    ", min=" + min +
                    ", isBST=" + isBST +
                    ", maxBSTSize=" + maxBSTSize +
                    ", maxBSTHead=" + maxBSTHead.value +
                    '}';
        }

        public Info(int max, int min, boolean isBST, int maxBSTSize, BtNode maxBSTHead) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
            this.maxBSTSize = maxBSTSize;
            this.maxBSTHead = maxBSTHead;
        }
    }

    public static Info process(BtNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int max = x.value;
        int min = x.value;
        boolean isBST = true;
        int maxBSTSize = 0;
        BtNode maxBSTHead = null;

        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            if (!leftInfo.isBST || x.value < leftInfo.max) {
                isBST = false;
            }
            maxBSTSize = leftInfo.maxBSTSize;
            maxBSTHead = leftInfo.maxBSTHead;
        }

        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            if (!rightInfo.isBST || x.value > rightInfo.min) {
                isBST = false;
            }
            maxBSTSize = Math.max(maxBSTSize, rightInfo.maxBSTSize);
            maxBSTHead = maxBSTSize == rightInfo.maxBSTSize ? rightInfo.maxBSTHead : maxBSTHead;
        }

        maxBSTHead = isBST ? x : maxBSTHead;

        if (isBST && leftInfo != null && rightInfo != null) {
            maxBSTSize = leftInfo.maxBSTSize + rightInfo.maxBSTSize + 1;
        } else if (isBST) {
            maxBSTSize++;
            maxBSTHead = x;
        }
        Info info = new Info(max, min, isBST, maxBSTSize, maxBSTHead);
//        System.out.println(info);
        return info;
    }

    public static BtNode getMaxSubBSTHead2(BtNode head) {
        if (head == null) {
            return null;
        }
        if (getMaxBSTSize(head) != 0) {
            return head;
        }
        BtNode leftNode = getMaxSubBSTHead2(head.left);
        BtNode rightNode = getMaxSubBSTHead2(head.right);
        return getMaxBSTSize(leftNode) > getMaxBSTSize(rightNode) ? leftNode : rightNode;
    }

    public static int getMaxBSTSize(BtNode head) {
        if (head == null) {
            return 0;
        }
        ArrayList<BtNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value < arr.get(i - 1).value) {
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

    public static void main(String[] args) {
        int testTimes = 500000;
        int maxLevel = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; (i < testTimes) && succeed; i++) {
            BtNode node = Tools.generateRandomBST(maxLevel, maxValue);
            if (getMaxSubBSTHead(node) != getMaxSubBSTHead2(node)) {
                succeed = false;
                System.out.println("getMaxSubBSTHead " + getMaxSubBSTHead(node).value);
                System.out.println("getMaxSubBSTHead2 " + getMaxSubBSTHead2(node).value);
                Tools.printBinaryTree(node);
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }

}
