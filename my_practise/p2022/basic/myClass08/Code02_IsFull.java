package my_practise.p2022.basic.myClass08;


import tool.BtNode;
import tool.Tools;

public class Code02_IsFull {
    public static boolean isFull1(BtNode head) {
        boolean ans = process1(head).isFull;
        return ans;
    }

    public static class Info1 {
        public int height;
        public boolean isFull;

        public Info1(int height, boolean isFull) {
            this.height = height;
            this.isFull = isFull;
        }
    }

    public static Info1 process1(BtNode head) {
        if (head == null) {
            return new Info1(0, true);
        }
        Info1 leftInfo = process1(head.left);
        Info1 rightInfo = process1(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = true;

        if (!leftInfo.isFull || !rightInfo.isFull || leftInfo.height != rightInfo.height) {
            isFull = false;
        }
        return new Info1(height, isFull);
    }

    public static boolean isFull2(BtNode head) {
        Info2 info = process2(head);
        return ((1 << info.height) - 1 == info.nodeNum);
    }

    public static class Info2 {
        public int nodeNum;
        public int height;

        public Info2(int nodeNum, int height) {
            this.nodeNum = nodeNum;
            this.height = height;
        }
    }

    public static Info2 process2(BtNode head) {
        if (head == null) {
            return new Info2(0, 0);
        }
        Info2 leftInfo = process2(head.left);
        Info2 rightInfo = process2(head.right);

        int nodeNum = leftInfo.nodeNum + rightInfo.nodeNum + 1;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        return new Info2(nodeNum, height);
    }


    public static void main(String[] args) {
        int testTimes = 500000;
        int maxLevel = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; (i < testTimes) && succeed; i++) {
            BtNode node = Tools.generateRandomBST(maxLevel, maxValue);
            if (isFull1(node) != isFull2(node)) {
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }
}
