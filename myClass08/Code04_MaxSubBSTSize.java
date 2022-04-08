package myClass08;

import tool.BtNode;
import tool.Tools;

import java.util.ArrayList;

public class Code04_MaxSubBSTSize {

    public static int getMaxBSTSize(BtNode head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxBSTSize;
    }

    public static class Info {
        public int max;
        public int min;
        public boolean isBST;
        public int maxBSTSize;

        public Info(int max, int min, boolean isBST, int maxBSTSize) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
            this.maxBSTSize = maxBSTSize;
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
        int maxBSTSize = 0;
        boolean isBST = true;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            if (!leftInfo.isBST || x.value < leftInfo.max) {
                isBST = false;
            }
            maxBSTSize = leftInfo.maxBSTSize;
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            if (!rightInfo.isBST || x.value > rightInfo.min) {
                isBST = false;
            }
            maxBSTSize = Math.max(maxBSTSize, rightInfo.maxBSTSize);
        }

        if (isBST && leftInfo != null && rightInfo != null) {
            maxBSTSize = leftInfo.maxBSTSize + rightInfo.maxBSTSize + 1;
        }else if(isBST){
            maxBSTSize++;
        }

        return new Info(max, min, isBST, maxBSTSize);
    }


    public static int getMaxBSTSize2(BtNode head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(getMaxBSTSize2(head.left), getMaxBSTSize2(head.right));
    }

    public static int getBSTSize(BtNode x) {
        if (x == null) {
            return 0;
        }
        ArrayList<BtNode> arr = new ArrayList<>();
        in(x, arr);
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
            BtNode node = Tools.generateRandomBST(maxLevel,maxValue);
            if (getMaxBSTSize(node) != getMaxBSTSize2(node)) {
                Tools.printBinaryTree(node);
                System.out.println(getMaxBSTSize(node));
                System.out.println(getMaxBSTSize2(node));
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }
}
