package my_practise.p2022.basic.myClass08;


import tool.BtNode;
import tool.Tools;

public class Code01_IsBalanced {
    public static boolean isBalanced1(BtNode head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(BtNode head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static class Info {
        public int height;
        public boolean isBalanced;

        public Info(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public static boolean isBalanced2(BtNode head) {
        boolean ans = process2(head).isBalanced;
        return ans;
    }

    public static Info process2(BtNode head) {
        if (head == null) {
            return new Info(0, true);
        }
        Info leftInfo = process2(head.left);
        Info rightInfo = process2(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;
        if (!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalanced = false;
        }
        return new Info(height, isBalanced);
    }


    public static void main(String[] args) {
        int testTimes = 500000;
        int maxLevel = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; (i < testTimes) && succeed; i++) {
            BtNode node = Tools.generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(node) != isBalanced2(node)) {
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }
}