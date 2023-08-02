package my_practise.p2023.pro.algorithm.KMP;

import tool.BtNode;
import tool.Node;
import tool.Tools;

import java.util.ArrayList;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.KMP
 * className:      TreeEqual
 * author:     BangDi
 * description:
 * 给定两棵二叉树的头节点head1和head2
 * 想知道head1中是否有某个子树的结构和head2完全一样
 * date:    2023/8/1 22:59
 * version:    1.0
 */
public class TreeEqual {

    // 暴力解 O(N * M)   N为大树的节点数,M为小树的节点数
    public static boolean containsTree1(BtNode bigHead, BtNode smallHead) {
        if (smallHead == null) {
            return true;
        }
        if (bigHead == null) {
            return false;
        }
        return isSameValueStructure(bigHead, smallHead) || containsTree1(bigHead.left, smallHead) || containsTree1(bigHead.right, smallHead);
    }

    public static boolean isSameValueStructure(BtNode h1, BtNode h2) {
        if (h1 == null && h2 == null) {
            return true;
        }
        if ((h1 == null || h2 == null)) {
            return false;
        }
        return h1.value == h2.value && isSameValueStructure(h1.left, h2.left) && isSameValueStructure(h1.right, h2.right);
    }

    // KMP 扩展 O(N)
    public static boolean containsTree2(BtNode bigHead, BtNode smallHead) {
        if (smallHead == null) {
            return true;
        }
        if (bigHead == null) {
            return false;
        }
        String[] arr = preSerial(bigHead);
        String[] matchArr = preSerial(smallHead);
        int[] next = getNext(matchArr);
        int x = 0;
        int y = 0;
        while (x < arr.length && y < matchArr.length) {
            if (arr[x].equals(matchArr[y])) {
                x++;
                y++;
            } else if (next[y] != -1) {
                y = next[y];
            } else {
                x++;
            }
        }
        return y == matchArr.length;
    }

    public static int[] getNext(String[] arr) {
        if (arr == null) {
            return null;
        }
        if (arr.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[arr.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while (i < arr.length) {
            if (arr[i - 1].equals(arr[cn])) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static String[] preSerial(BtNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<String> arr = new ArrayList<>();
        process(head, arr);
        return arr.toArray(new String[0]);
    }

    public static void process(BtNode head, ArrayList<String> arr) {
        if (head == null) {
            arr.add("null");
            return;
        }
        arr.add(String.valueOf(head.value));
        process(head.left, arr);
        process(head.right, arr);
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
        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            BtNode big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            BtNode small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean ans1 = containsTree1(big, small);
            boolean ans2 = containsTree2(big, small);
            if (ans1){
                String[] arr = preSerial(big);
                String[] matchArr = preSerial(small);
                Tools.printStringArray(arr);
                Tools.printStringArray(matchArr);
                System.out.println("=================");
            }
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }
}
