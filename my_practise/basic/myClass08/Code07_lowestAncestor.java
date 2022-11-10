package my_practise.basic.myClass08;

import tool.BtNode;
import tool.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Code07_lowestAncestor {

    public static BtNode lowestAncestor1(BtNode head, BtNode a, BtNode b) {
        if (head == null || a == null || b == null) {
            return null;
        }
        return process1(head, a, b).lowestAncestor;
    }

    public static class Info {
        public boolean isAAncestor;
        public boolean isBAncestor;
        public BtNode lowestAncestor;

        public Info(boolean isAAncestor, boolean isBAncestor, BtNode lowestAncestor) {
            this.isAAncestor = isAAncestor;
            this.isBAncestor = isBAncestor;
            this.lowestAncestor = lowestAncestor;
        }
    }

    public static Info process1(BtNode x, BtNode a, BtNode b) {
        if (x == null) {
            return new Info(false, false, null);
        }

        Info leftInfo = process1(x.left, a, b);
        Info rightInfo = process1(x.right, a, b);


        if (leftInfo.lowestAncestor != null) {
            return leftInfo;
        }
        if (rightInfo.lowestAncestor != null) {
            return rightInfo;
        }

        boolean isAAncestor = x == a || leftInfo.isAAncestor || rightInfo.isAAncestor;
        boolean isBAncestor = x == b || leftInfo.isBAncestor || rightInfo.isBAncestor;

        BtNode lowestAncestor = isAAncestor && isBAncestor ? x : null;

        return new Info(isAAncestor, isBAncestor, lowestAncestor);
    }

    public static BtNode[] getRandomBTAndAB(int maxLevel, int maxValue) {
        BtNode head = Tools.generateRandomBST(maxLevel, maxValue);
        if (head == null) {
            return null;
        }
        BtNode a = null;
        BtNode b = null;
        ArrayList<BtNode> arr = new ArrayList<>();
        Tools.in(head, arr);
        a = arr.get((int) (Math.random() * arr.size()));
        b = arr.get((int) (Math.random() * arr.size()));
        return new BtNode[]{head, a, b};
    }

    public static BtNode lowestAncestor2(BtNode head, BtNode a, BtNode b) {
        if (head == null || a == null || b == null) {
            return null;
        }
        HashMap<BtNode, BtNode> map = new HashMap<>();
        inHashMap(head, map, null);
        BtNode lowestAncestor = null;
        BtNode bAncestor = b;
        while (lowestAncestor == null) {
            bAncestor = b;
            while (bAncestor != null) {
//                System.out.println("a:" + a.value + " b:" + bAncestor.value);
                if (a == bAncestor) {
                    lowestAncestor = bAncestor;
                }
                bAncestor = map.get(bAncestor);
            }
            a = map.get(a);
        }

        return lowestAncestor;
    }

    public static void inHashMap(BtNode x, HashMap<BtNode, BtNode> map, BtNode parent) {
        if (x == null) {
            return;
        }
        map.put(x, parent);
        inHashMap(x.left, map, x);
        inHashMap(x.right, map, x);
    }

    public static BtNode lowestAncestor3(BtNode head, BtNode a, BtNode b) {
        if (head == null || a == null || b == null) {
            return null;
        }
        HashMap<BtNode, BtNode> parentMap = new HashMap<>();
        fillParentMap(head, parentMap);
        HashSet<BtNode> set = new HashSet<>();
        BtNode ans = null;
        BtNode cur = a;
        while (cur != null) {
            set.add(cur);
            cur = parentMap.get(cur);
        }
        cur = b;
        while (!set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(BtNode head, HashMap<BtNode, BtNode> map) {
        if (head.left != null) {
            map.put(head.left, head);
            fillParentMap(head.left, map);
        }
        if (head.right != null) {
            map.put(head.right, head);
            fillParentMap(head.right, map);
        }
    }


    public static void main(String[] args) {
        int testTimes = 500000;
        int maxLevel = 10;
        int maxValue = 10;
        boolean succeed = true;

        BtNode[] ans = null;
        BtNode head = null;
        BtNode a = null;
        BtNode b = null;
        for (int i = 0; i < testTimes; i++) {
            ans = getRandomBTAndAB(maxLevel, maxValue);
            if (ans == null) {
                continue;
            }
            head = ans[0];
            a = ans[1];
            b = ans[2];
//            Tools.printBinaryTree(head);

            if (lowestAncestor1(head, a, b) != lowestAncestor2(head, a, b) || lowestAncestor2(head, a, b) != lowestAncestor3(head,a,b)) {
                succeed = false;
            }
        }

        System.out.println(succeed ? "Nice" : "F");

//        for (int i = 0; i < 1000; i++) {
//            ans = getRandomBTAndAB(3, 10);
//            if (ans == null) {
//                continue;
//            }
//            head = ans[0];
//            a = ans[1];
//            b = ans[2];
//            Tools.printBinaryTree(head);
//            System.out.println("a:" + a.value + " b:" + b.value);
//            System.out.println(lowestAncestor3(head, a, b).value);
//        }
    }
}
