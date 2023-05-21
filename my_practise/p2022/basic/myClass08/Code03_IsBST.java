package my_practise.p2022.basic.myClass08;

import tool.BtNode;
import tool.Tools;

import java.util.ArrayList;

public class Code03_IsBST {

    public static boolean isBST(BtNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isSearch;
    }

    public static class Info {
        public int maxValue;
        public int minValue;
        public boolean isSearch;

        public Info(int max, int min, boolean isBal) {
            maxValue = max;
            minValue = min;
            isSearch = isBal;
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
        if(leftInfo != null){
            max = Math.max(leftInfo.maxValue,max);
            min = Math.min(leftInfo.minValue,min);
        }
        if(rightInfo != null){
            max = Math.max(rightInfo.maxValue,max);
            min = Math.min(rightInfo.minValue,min);
        }

        boolean subIsSearch = leftInfo == null || leftInfo.isSearch;
        subIsSearch = rightInfo == null ? subIsSearch : subIsSearch & rightInfo.isSearch;
        boolean isSearch = true;


        // 当左右两孩子的信息不为空，只要有一个违反就false
        if (!subIsSearch || (leftInfo != null && leftInfo.maxValue > x.value) || (rightInfo != null && rightInfo.minValue < x.value)) {
            isSearch = false;
        }
//        System.out.println("Node " + x.value + " maxValue" + max + " minValue" + min + " isSearch" + isSearch);
        return new Info(max, min, isSearch);
    }

    public static boolean isBST2(BtNode head) {
        if (head == null) {
            return true;
        }
        boolean ans = true;
        ArrayList<BtNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value < arr.get(i - 1).value) {
                ans = false;
            }
        }
        return ans;
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
        int maxValue = 10;
        int maxLevel = 10;
        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            BtNode node = Tools.generateRandomBST(maxLevel, maxValue);
            if (isBST(node) != isBST2(node)) {
                System.out.println("isBTS " + isBST(node));
                System.out.println("isBTS2 " + isBST2(node));
                Tools.printBinaryTree(node);
                succeed = false;
                return;
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }
}